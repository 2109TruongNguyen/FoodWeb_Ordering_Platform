package dao;

import dto.PersonalFood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtill;

public class PersonalFoodDAO {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<PersonalFood> getFoodsByDayOfWeek(int personalID, int weekDay, int weekIndex) {
        List<PersonalFood> list = new ArrayList<>();
        PersonalFood foodPlan = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String query = "select [PF_ID],[PL_ID],[F_ID],[WeekDay],[WeekIndex],[Meal]\n"
                        + "from [dbo].[tblPERSONAL_FOOD]\n"
                        + "where [PL_ID]=? and [WeekDay]=? and [WeekIndex]=?";
                ps = cn.prepareStatement(query);
                ps.setInt(1, personalID);
                ps.setInt(2, weekDay);
                ps.setInt(3, weekIndex);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        int plID = rs.getInt(2);
                        int fID = rs.getInt(3);
                        int dayOfWeek = rs.getInt(4);
                        int week = rs.getInt(5);
                        int meal = rs.getInt(6); // Retrieve the new Meal attribute

                        foodPlan = new PersonalFood(id, plID, fID, dayOfWeek, week, meal);
                        list.add(foodPlan);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<PersonalFood> getFoodsByIndexOfWeek(int personalID, int weekIndex) {
        List<PersonalFood> list = new ArrayList<>();
        PersonalFood foodPlan = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String query = "select [PF_ID],[PL_ID],[F_ID],[WeekDay],[WeekIndex],[Meal]\n"
                        + "from [dbo].[tblPERSONAL_FOOD]\n"
                        + "where [PL_ID]=? and [WeekIndex]=?";
                ps = cn.prepareStatement(query);
                ps.setInt(1, personalID);
                ps.setInt(2, weekIndex);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        int plID = rs.getInt(2);
                        int fID = rs.getInt(3);
                        int dayOfWeek = rs.getInt(4);
                        int week = rs.getInt(5);
                        int meal = rs.getInt(6); // Retrieve the new Meal attribute

                        foodPlan = new PersonalFood(id, plID, fID, dayOfWeek, week, meal);
                        list.add(foodPlan);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public int insertFoodPlan(int personalID, int foodID, int weekDay, int weekIndex, int meal) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "insert into [dbo].[tblPERSONAL_FOOD] ([PL_ID],[F_ID],[WeekDay],[WeekIndex],[Meal])\n"
                        + "values (?,?,?,?,?)";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, personalID);
                ps.setInt(2, foodID);
                ps.setInt(3, weekDay);
                ps.setInt(4, weekIndex);
                ps.setInt(5, meal); // Set the new Meal attribute
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int deletePerFoodPlan(int PersonalFoodID) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblPERSONAL_FOOD] WHERE [PF_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, PersonalFoodID);
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int deleteFoodPlan(int personalID) {
        int result = 0;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "DELETE FROM [dbo].[tblPERSONAL_FOOD] WHERE [PL_ID] = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, personalID);
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
