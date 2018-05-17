package yazdaniscodelab.expenseyourtrackdemoapps.Model;

/**
 * Created by Yazdani on 10/28/2017.
 */

public class Data {

    int ammount;
    String id;
    String type;
    String note;

    public Data(int ammount,String id,String type,String note){
        this.ammount=ammount;
        this.id=id;
        this.type=type;
        this.note=note;
    }


    public Data(){

    }

    public int getAmmount(){
        return ammount;
    }

    public void setAmmount(int ammount){
        this.ammount=ammount;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }


//    public int getAmmount(){
//        return ammount;
//    }
//
//    public void setAmmount(int ammount){
//        this.ammount=ammount;
//    }
//
//    public String getId(){
//        return id;
//    }
//
//    public void setId(String id){
//        this.id=id;
//    }
//
//    public String getType(){
//        return type;
//    }
//
//    public void setType(String type){
//        this.type=type;
//    }
//
//    public String getNote(){
//        return note;
//    }
//
//    public void setNote(String note){
//        this.note=note;
//    }
//

}
