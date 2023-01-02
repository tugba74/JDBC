import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02_DDL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*

        A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
      dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.
       1) execute() metodu
       2) executeUpdate() metodu.

   B) - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur.
      - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken,
        DML islemlerinde true deger dondurur.
      - Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi
        durumlarda tercih edilmektedir.

   C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
      - bu islemlerde islemden etkilenen satir sayisini dondurur.
      - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.


         */

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "17187434");
        Statement st = con.createStatement();




    // ORNEK 1: Isciler tablosunu siliniz
    /*
        String dropTable = "DROP TABLE isciler";
        //execute boolean döndürdüğü için bize veri döndürmez gider tabloyu mysql den siler görmek için sout yapmak lazım
        if (!st.execute(dropTable))
            System.out.println("isciler tablosu silindi!");
        else
            System.out.println("isciler tablosu silme islemi gerçekleşmedi");
     */

    /*===================================================================
     ORNEK 2:isiler adinda bir tablo olusturunuz id int,
     birim varchar(10), maas int
    ====================================================================*/

     /*
     String createTable = "CREATE TABLE  isciler"+
                          "(id INT, " +
                          "birim VARCHAR(10)," +
                          "maas INT)";

        if (!st.execute(createTable)) // execute false döndürür tersi yapıyoruz ki doğru yazdırsın
            System.out.println("isciler tablosu create edildi!");
        else
            System.out.println("isciler tablosu create islemi gerçekleşmedi");
      */
     /*===================================================================
     ORNEK 2:isciler tablosuna yeni bir kayıt (80,'ARGE', 4000) EKLEYELİM
    ====================================================================*/

        String insertVeri = "INSERT INTO isciler VALUES (80, 'ARGE', 4000)";

        int satirsayisi = st.executeUpdate(insertVeri); //integer döndürür

        System.out.println("Islemden etkilenen satir sayisi : " + satirsayisi);

        // kısıtlma olmadığı için unique gibi primary key gibi işlemi diğer işlem için kapatmaya gerek yok. yoksa diğer sorularda diğer konutları kapatmıştık
        //DDL KOMUTLARI BİR KEZ ÇALIŞIR, DML İSE BİRDEN FAZLA ÇALIŞIR
       /*=======================================================================
          ORNEK4: isciler tablosuna birden fazla yeni kayıt ekleyelim.

            INSERT INTO isciler VALUES(70, 'HR', 5000)
            INSERT INTO isciler VALUES(60, 'LAB', 3000)
            INSERT INTO isciler VALUES(50, 'ARGE', 4000) (hepsi tek seferde calismaz o nedenle array ve for each ile olusturacağız)
         ========================================================================*/

        //  System.out.println("=============== 1. Yontem ==============");

        //  String [] sorgular = {"INSERT INTO isciler VALUES(70, 'HR', 5000)",
        //                        "INSERT INTO isciler VALUES(60, 'LAB', 3000)",
        //                        "INSERT INTO isciler VALUES(50, 'ARGE', 4000)"};
        //  int count
        //  for (String each : sorgular) {
        //      count+= st.executeUpdate(each);
        //  }
        //  System.out.println(count + " satir eklendi!");

        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin
        // yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
        // bu kotu bir yaklasimdir.

       // System.out.println("=============== 2. Yontem ==============");

        // 2.YONTEM (addBatch ve executeBatch() metotlari ile)
        // ----------------------------------------------------
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve executeBatch()
        // metodu ile veritabanina bir kere gonderilebilir.
        // executeBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda
        // etkilenen satir sayisini gosterir.
/*
        String [] sorgular2 = {"INSERT INTO isciler VALUES(40, 'HR', 6000)",
                               "INSERT INTO isciler VALUES(30, 'LAB', 2000)",
                               "INSERT INTO isciler VALUES(20, 'ARGE', 5000)"};

        for (String each : sorgular2) {
            st.addBatch(each);
        }

        st.executeBatch();

        System.out.println("Satirlar eklendi");
*/
        /*=======================================================================
		  ORNEK5: isciler tablosundaki maasi 5000'den az olan iscilerin maasina
		   %10 zam yapiniz
		========================================================================*/

        String update = "UPDATE isciler " +
                "SET maas=maas*1.1 " +
                "WHERE maas<5000";

        int satir = st.executeUpdate(update);
        System.out.println( satir +" satir guncellendi! ");


        con.close();
        st.close();

    }
}

