package fr.epsi.ateliermspr;

public class CheckingStringNumeric {
    public static boolean isNumenic(String strValue){
        boolean is_numeric = true;
        for(int i = 0; i < strValue.length(); i++){
            if (!Character.isDigit(strValue.charAt(i))){
                is_numeric = false;
                break;
            }
        }
        return is_numeric;
    }
}
