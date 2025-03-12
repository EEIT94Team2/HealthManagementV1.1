package dao.course;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import service.course.*;
import model.course.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CourseDAO2")
public class CourseDAO2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }
    




    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert":
                doInsert(request, response);
                break;
            case "delete":
                doDelete(request, response);
                break;
            case "updatedata":
                doUpdateData(request, response);
                break;
            case "update":
                doUpdate(request, response);
                break;
            case "find":
                doFind(request, response);
                break;
            case "findAll":
                doFindAll(request, response);
                break;
            default:
                break;
        }
    }

    // 新增課程

    protected void doInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 取得課程的資料
        String course_id = request.getParameter("course_id");
        String course_name = request.getParameter("course_name");
        String course_description = request.getParameter("course_description");
        String course_date = request.getParameter("course_date");
        String coach_id = request.getParameter("coach_id");
        String coach_name = request.getParameter("coach_name");
        String course_time = request.getParameter("course_time");
        String max_capacity = request.getParameter("max_capacity");

        // 建立 Course 物件
    	Course course = new Course();
		course.setcourse_id(course_id);
		course.setcourse_name(course_name);
		course.setcourse_description(course_description);
		course.setcourse_date(course_date);
		course.setcoach_id(coach_id);
		course.setcoach_name(coach_name);
		course.setcourse_time(course_time);
		course.setmax_capacity(max_capacity);
        try {
            // 使用 CourseService 插入課程
            courseService.insert(course);
            
            // 成功訊息
            String successMessage = "課程新增成功！";
            request.setAttribute("successMessage", successMessage);
            request.setAttribute("course", course);
            
            // 轉發到 JSP 頁面
            request.getRequestDispatcher("/jsp/course/insert.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 可以加上錯誤訊息並返回原頁面，告知用戶插入失敗
            request.setAttribute("errorMessage", "課程新增失敗，請稍後再試！");
            request.getRequestDispatcher("/jsp/course/insert.jsp").forward(request, response);
        }
    }

    // 刪除課程
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        
        try {
            // 先查詢課程是否存在
            Course course = courseService.find(course_id);
            if (course != null) {
                // 如果課程存在，則執行刪除操作
                courseService.delete(course_id);
                String successMessage = "課程刪除成功！";
                request.setAttribute("successMessage", successMessage);  // 設定刪除成功訊息
                request.setAttribute("course_id", course_id);
                request.getRequestDispatcher("/jsp/course/delete.jsp").forward(request, response);
            } else {
                // 如果課程不存在，顯示錯誤訊息
                String errorMessage = "找不到對應的課程，無法刪除！";
                request.setAttribute("errorMessage", errorMessage);  // 設定錯誤訊息
                request.getRequestDispatcher("/jsp/course/delete.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 發生錯誤時，顯示錯誤訊息
            request.setAttribute("errorMessage", "刪除過程中出錯，請稍後再試！");
            request.getRequestDispatcher("/jsp/course/delete.jsp").forward(request, response);
        }
    }


    

 // 查詢單筆課程
    protected void doFind(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        try {
            // 查詢課程
            Course course = courseService.find(course_id);
            
            if (course != null) {
                // 如果找到課程，將課程資料設置到 request 屬性中
                request.setAttribute("course", course);
                // 在這裡轉發到顯示課程資料頁面
                request.getRequestDispatcher("/jsp/course/schedule.jsp").forward(request, response);
            } else {
                // 如果未找到課程，設置錯誤訊息並提示用戶重新輸入
                request.setAttribute("errorMessage", "未找到對應的課程，請重新輸入課程編號！");
                // 返回查詢頁面（此處可以顯示錯誤訊息）
                request.getRequestDispatcher("/jsp/course/schedule.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 發生錯誤時，設置錯誤訊息並轉發回原來的查詢頁面
            request.setAttribute("errorMessage", "查詢過程中出錯，請稍後再試！");
            request.getRequestDispatcher("/jsp/course/schedule.jsp").forward(request, response);
        }
    }

    // 查詢所有課程
    protected void doFindAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Course> courses = courseService.findAll();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/jsp/course/allschedule.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 修改課程 doUpdateData
    protected void doUpdateData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        try {
            // 先查詢課程是否存在
            Course course = courseService.find(course_id);

            if (course != null) {
                // 如果課程存在，將課程資料設置到 request 屬性中
                request.setAttribute("course", course);
                request.getRequestDispatcher("/jsp/course/updatedata.jsp").forward(request, response);
            } else {
                // 如果課程不存在，顯示錯誤訊息
                String errorMessage = "找不到對應的課程，無法進行修改！";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/jsp/course/updatedata.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String course_name = request.getParameter("course_name");
        String course_description = request.getParameter("course_description");
        String course_date = request.getParameter("course_date");
        String coach_id = request.getParameter("coach_id");
        String coach_name = request.getParameter("coach_name");
        String course_time = request.getParameter("course_time");
        String max_capacity = request.getParameter("max_capacity");

        // 先查詢課程是否存在
        Course existingCourse = null;
		try {
			existingCourse = courseService.find(course_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (existingCourse != null) {
            // 如果課程存在，進行更新操作
            Course course = new Course();
            course.setcourse_id(course_id);
            course.setcourse_name(course_name);
            course.setcourse_description(course_description);
            course.setcourse_date(course_date);
            course.setcoach_id(coach_id);
            course.setcoach_name(coach_name);
            course.setcourse_time(course_time);
            course.setmax_capacity(max_capacity);

            try {
                // 呼叫 service 層的 update 方法進行資料庫更新
                courseService.update(course);

                String successMessage = "課程修改成功！";
                request.setAttribute("successMessage", successMessage);
                request.setAttribute("course", course);
                request.getRequestDispatcher("/jsp/course/updatedata.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                String errorMessage = "課程修改失敗，請稍後再試！";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/jsp/course/update.jsp").forward(request, response);
            }
        } else {
            // 如果課程不存在，顯示錯誤訊息
            String errorMessage = "找不到對應的課程，無法進行修改！";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/jsp/course/update.jsp").forward(request, response);
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
