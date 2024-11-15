/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.personal_plan;

import dao.FoodDAO;
import dao.PersonalFoodDAO;
import dao.PersonalWeekDAO;
import dao.WeeklyMenuDAO;
import dao.WeeklyMenuItemDAO;
import dto.PersonalFood;
import dto.PersonalWeek;
import dto.WeeklyMenu;
import dto.WeeklyMenuItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class actionEditPlanServlet extends HttpServlet {

    PersonalFoodDAO pfDao = new PersonalFoodDAO();
    PersonalWeekDAO pwDao = new PersonalWeekDAO();
    FoodDAO foodDao = new FoodDAO();
    WeeklyMenuDAO weekDao = new WeeklyMenuDAO();
    String url = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String actionEdit = request.getParameter("ActionEdit");
            String planID = request.getParameter("planID");

            switch (actionEdit) {
                case "deleteFood":
                    deleteFood(request, response);
                    break;
                case "addFood":
                    addFood(request, response);
                    break;
                case "addFoodPage":
                    addFoodPage(request, response);
                    url = "view/user/viewFoodPersonal.jsp";
                    break;
                case "addWeeklyMenu":
                    addWeeklyMenu(request, response);
                    break;
                case "addWeeklyMenuPage":
                    addWeeklyMenuPage(request, response);
                    url = "view/user/viewPersonalWeek.jsp";
                    break;
                case "deleteWeeklyMenu":
                    deleteWeeklyMenu(request, response);
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    //----------------------------
    private void deleteFood(HttpServletRequest request, HttpServletResponse response) {
        String foodID = request.getParameter("PersonalFoodID");
        int idFood = Integer.parseInt(foodID);

        pfDao.deletePerFoodPlan(Integer.parseInt(foodID));
    }

    //----------------------------
    private void addFood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        PersonalFood infor = (PersonalFood) session.getAttribute("PersonalFood");
        int foodID = Integer.parseInt(request.getParameter("foodId"));

        int rs = pfDao.insertFoodPlan(infor.getPersonalID(), foodID, infor.getWeekDay(), infor.getWeekIndex(), infor.getMeal());
        if (rs == 0) {
            request.setAttribute("msg", "ID món ăn có vẻ không đúng!!!");
        }
        url = "MainController?action=editPlan&planID=" + infor.getPersonalID();
    }

    //----------------------------
    private void addFoodPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String planID = request.getParameter("planID");
        int weekDay = Integer.parseInt(request.getParameter("weekDay"));
        int weekIndex = Integer.parseInt(request.getParameter("weekIndex"));
        int meal = Integer.parseInt(request.getParameter("meal"));
        PersonalFood f = new PersonalFood(Integer.parseInt(planID), weekDay, weekIndex, meal);

        session.setAttribute("PersonalFood", f);
        request.setAttribute("ListFoods", foodDao.getFoods(""));
    }

    //----------------------------
    private void addWeeklyMenu(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String weekID= request.getParameter("weekID");
        PersonalWeek infor= (PersonalWeek) session.getAttribute("PersonalWeek");
        
        PersonalWeekDAO wDao = new PersonalWeekDAO();
        wDao.insertWeekPlan(infor.getPersonalID(), Integer.parseInt(weekID), infor.getWeekIndex());
        url = "MainController?action=editPlan&planID=" + infor.getPersonalID();
    }

    //----------------------------
    private void addWeeklyMenuPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String planID = request.getParameter("planID");
        int weekIndex = Integer.parseInt(request.getParameter("weekIndex"));

        PersonalWeek pw = new PersonalWeek(Integer.parseInt(planID), weekIndex);
        

        WeeklyMenuDAO weeklyDAO = new WeeklyMenuDAO();
        WeeklyMenuItemDAO weeklyItemDAO = new WeeklyMenuItemDAO();

        List<WeeklyMenu> weeklyList = weeklyDAO.getMenuByName("");
        HashMap<WeeklyMenu, List<WeeklyMenuItem>> weeklyItemList = new HashMap<>();

        if (weeklyList != null && !weeklyList.isEmpty()) {
            for (WeeklyMenu i : weeklyList) {
                weeklyItemList.putIfAbsent(i, weeklyItemDAO.getMenuItemByID(i.getId()));
            }
        }
        session.setAttribute("PersonalWeek", pw);
        request.setAttribute("WeeklyList", weeklyList);
        request.setAttribute("WeeklyItemList", weeklyItemList);
    }

    //----------------------------
    private void deleteWeeklyMenu(HttpServletRequest request, HttpServletResponse response) {
        String planID = request.getParameter("planID");
        int weekID = Integer.parseInt(request.getParameter("WeeklyMenuID"));
        int weekIndex = Integer.parseInt(request.getParameter("weekIndex"));

        pwDao.deletePlanByWeekIndex(Integer.parseInt(planID), weekIndex, weekID);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
