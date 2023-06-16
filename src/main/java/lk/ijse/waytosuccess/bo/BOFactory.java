package lk.ijse.waytosuccess.bo;

import lk.ijse.waytosuccess.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,ITEM,SERVICE,SUPPLIER,CHANGE_PASSWORD,LOGIN_PAGE,PLACE_ORDER,ORDER,ORDER_DETAIL,RETURN_ITEM
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case CHANGE_PASSWORD:
                return new ChangePasswordBOImpl();
            case LOGIN_PAGE:
                return new LoginPageBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailBOImpl();
            case RETURN_ITEM:
                return new ReturnItemBOImpl();
            default:
                return null;
        }
    }
}
