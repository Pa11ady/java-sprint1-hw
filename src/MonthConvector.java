 public class MonthConvector {
     //Константа только ссылка, но массив к сожалению не константный
     public static final String[] MONTH = {
             "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
             "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
     };

     public static String getMonthName(int index) {
         if (index < 1 || index > 12) {
             return "";
         }
         return MONTH[index];
     }
}
