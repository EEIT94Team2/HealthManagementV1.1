package model.fitness;

import model.User;

public class ExerciseRecord {
    private Integer  recordId;
    private Integer userId;
    private String exerciseType;
    private int exerciseDuration;
    private double caloriesBurned;  
    private String exerciseDate;
    private User user;
  

    public ExerciseRecord(Integer recordId,Integer userId, String exerciseType, int exerciseDuration,
                          double caloriesBurned, String exerciseDate) {
        super();
        this.recordId = recordId;
        this.userId = userId;
        this.exerciseType = exerciseType;
        this.exerciseDuration = exerciseDuration;
        this.caloriesBurned = caloriesBurned;
        this.exerciseDate = exerciseDate;
     
    }

    public ExerciseRecord() {
        // Default constructor
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(int exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;  
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;  
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

 
}
