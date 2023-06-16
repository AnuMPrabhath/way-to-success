package lk.ijse.waytosuccess.dao;

import lk.ijse.waytosuccess.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,ITEM,SERVICE,SUPPLIER,CHANGE_PASSWORD,LOGIN_PAGE,ORDER,ORDER_DETAILS,RETURN_ITEM,PLACE_ORDER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case CHANGE_PASSWORD:
                return new ChangePasswordDAOImpl();
            case LOGIN_PAGE:
                return new LoginPageDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            case RETURN_ITEM:
                return new ReturnItemDAOImpl();
            case PLACE_ORDER:
                return new PlaceOrderDAOImpl();
            default:
                return null;
        }
    }
}
