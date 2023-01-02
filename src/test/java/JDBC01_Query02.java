import java.sql.*;

public class JDBC01_Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "17187434");
        Statement st = con.createStatement();

        /*=======================================================================
         ORNEK2: Ankara'da yasayan calisanlarin isim ve maaslarini  maas ters
          sirali olarak listeleyiniz.
        ========================================================================*/
        ResultSet veri =st.executeQuery("SELECT isim, maas FROM calisanlar WHERE sehir='Ankara' ORDER BY maas DESC ");

        while (veri.next()){
            System.out.println( veri.getString("isim")+ "-" + veri.getInt("maas"));
        }

        System.out.println();

       // ornek calismam
        // istanbulda çalışanların isim ve id lerini sıralı olarak listeleyiniz


       ResultSet veri1 = st.executeQuery("SELECT isim, id FROM calisanlar WHERE sehir='Istanbul' ORDER BY isim ");

       while (veri1.next()) {
           System.out.println(veri1.getString("isim")+ "-" + veri1.getInt("id"));
       }


        /*=======================================================================
          ORNEK3: Maasi en yuksek 3 kisinin adini, yasadigi sehri ve maasini
           maas sirali listeyiniz.
        ========================================================================*/

        System.out.println("===================ORNEK 3 ===========================");

        String sorgu = "SELECT isim,sehir, maas FROM calisanlar ORDER BY maas DESC LIMIT 3";

        ResultSet veri2 =st.executeQuery(sorgu);

        while (veri2.next()) {
            System.out.println(veri2.getString(1) + "-" + veri2.getString(2) + "-" + veri2.getInt(3));
        }

         // NOT1 : Sorgulama icin get ile istenirse sütun (field) ismini yazabilecegimiz gibi sutun index
         // (field olusturulma sirasina gore) yazilabilir.

         //NOT2: Sorgumuzda SELECT'ten sonra sadece belli fieldları döndürmesini istiyorsak
         // get ile cağırdığımız field indexleri sorguda belirttiğimiz sırayla ifade etmemiz gerekiyor




        con.close();
        st.close();
        veri.close();

        }
    }

