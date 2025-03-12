package service.fitness;

import model.fitness.ExerciseRecord;
import dao.fitness.ExerciseRecordDAO;
import util.DBUtil; // 引入 DBUtil

import java.sql.*;
import java.util.List;

public class ExerciseRecordService {

	// 獲取資料庫連接（改用 DBUtil 直接連線）
	public Connection getConnection() throws SQLException {
		return DBUtil.getConnection(); // 直接使用 DBUtil 連線，不用 JNDI
	}

	// 新增運動紀錄
	public boolean addExerciseRecord(ExerciseRecord record) throws SQLException {
		double caloriesBurned = calculateCaloriesBurned(record);
		record.setCaloriesBurned(caloriesBurned);

		try (Connection conn = getConnection()) {
			ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
			return dao.addExerciseRecord(record);
		}
	}

	// 更新運動紀錄
	public boolean updateExerciseRecord(ExerciseRecord record) throws SQLException {
		double caloriesBurned = calculateCaloriesBurned(record);
		record.setCaloriesBurned(caloriesBurned);

		try (Connection conn = getConnection()) {
			ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
			return dao.updateExerciseRecord(record);
		}
	}

	// 計算卡路里
	public double calculateCaloriesBurned(ExerciseRecord record) throws SQLException {
		try {
			double userWeight = getUserWeight(record.getUserId());

			if (userWeight == 0) {
				throw new SQLException("User weight is zero or invalid!");
			}

			try (Connection conn = getConnection()) {
				ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
				double metValue = dao.getMETSValue(record.getExerciseType());

				if (metValue == 0) {
					throw new SQLException("METS value not found for exercise type: " + record.getExerciseType());
				}

				double exerciseDurationInHours = record.getExerciseDuration() / 60.0;
				return userWeight * metValue * exerciseDurationInHours;
			}

		} catch (SQLException e) {
			System.out.println("Error calculating calories: " + e.getMessage());
			throw e;
		}
	}

	// 獲取用戶體重
	private double getUserWeight(int userId) throws SQLException {
		String sql = "SELECT Weight FROM BodyMetrics WHERE UserID = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getDouble("Weight");
				} else {
					throw new SQLException("user ID: " + userId +" 請先輸入體重");
				}
			}
		}
	}

	// 刪除運動紀錄
	public boolean deleteExerciseRecord(int recordId) throws SQLException {
		try (Connection conn = getConnection()) {
			ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
			return dao.deleteExerciseRecord(recordId);
		}
	}

	// 查詢用戶的所有運動紀錄
	public List<ExerciseRecord> getExerciseRecords(int userId) throws SQLException {
		try (Connection conn = getConnection()) {
			ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
			return dao.getExerciseRecords(userId);
		}
	}

	// 查詢所有用戶的所有運動資訊
	public List<ExerciseRecord> getAllExerciseRecords() throws SQLException {
		try (Connection conn = getConnection()) {
			ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
			return dao.getAllExerciseRecords();
		}
	}
	// 查詢用戶的運動資訊模糊查詢
		public List<ExerciseRecord> getExerciseRecordByName(String name) throws SQLException {
			try (Connection conn = getConnection()) {
				ExerciseRecordDAO dao = new ExerciseRecordDAO(conn);
				return dao.getExerciseRecordsByName(name);
			}
		}
}
