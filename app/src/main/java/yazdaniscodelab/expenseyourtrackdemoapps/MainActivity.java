package yazdaniscodelab.expenseyourtrackdemoapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.expenseyourtrackdemoapps.Model.Data;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private  EditText editTextTYPE;
    private EditText editTextNote;
    private EditText editTextAmmount;
    private Button buttonSave;
    private Button buttonCancel;



//    credit data


    private EditText editTextCredit_TYPE;
    private EditText editTextCredit_Note;
    private EditText editTextCredit_Ammount;
    private Button Credit_buttonSave;
    private Button Credit_buttonCancel;


    //Firebase database..

    private DatabaseReference CreditDatabase;
    private DatabaseReference DebidDatabase;
    private DatabaseReference maindatabase;


    //Credit data variable

    private String ammount_Credit;
    private String type_Credit;
    private String note_Credit;

    //Debit data variable

    private String ammount_Debit;
    private String type_Debit;
    private String note_Debit;


    FloatingActionButton fab_plus,add_Debit,add_Credit;

    TextView two,three;

    Animation FadeOpen,FadeClose;

    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Track Your Expense");
        setSupportActionBar(toolbar);


        CreditDatabase= FirebaseDatabase.getInstance().getReference().child("CreditData");
        DebidDatabase=FirebaseDatabase.getInstance().getReference().child("DebitData");
        maindatabase=FirebaseDatabase.getInstance().getReference().child("MAINdATABASE");




        DrawerLayout drwer = (DrawerLayout) findViewById(R.id.drawer_layout_id);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drwer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        drwer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_account);

        two=(TextView)findViewById(R.id.two);
        three=(TextView)findViewById(R.id.three);
        fab_plus=(FloatingActionButton)findViewById(R.id.fab_plus_xml);
        add_Debit=(FloatingActionButton)findViewById(R.id.fab_fab_xml);
        add_Credit=(FloatingActionButton)findViewById(R.id.fab_twitter_xml);
        FadeOpen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_open);
        FadeClose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        fab_plus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Add();

                if (isOpen){

                    add_Debit.startAnimation(FadeClose);
                    add_Credit.startAnimation(FadeClose);
                    add_Debit.setClickable(false);
                    add_Credit.setClickable(false);
                    two.startAnimation(FadeClose);
                    three.startAnimation(FadeClose);
                    two.setClickable(false);
                    three.setClickable(false);
                    isOpen=false;
                }

                else {

                    add_Debit.startAnimation(FadeOpen);
                    add_Credit.startAnimation(FadeOpen);
                    two.startAnimation(FadeOpen);
                    three.startAnimation(FadeOpen);
                    two.setClickable(true);
                    three.setClickable(true);
                    add_Debit.setClickable(true);
                    add_Credit.setClickable(true);
                    isOpen=true;
                }
            }
        });
    }



    public  void Add(){


//        credit button...

        add_Credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creditDataADD();


//                For Remove floting button when clicked on credit icon..

                if (isOpen){

                    add_Debit.startAnimation(FadeClose);
                    add_Credit.startAnimation(FadeClose);
                    add_Debit.setClickable(false);
                    add_Credit.setClickable(false);
                    two.startAnimation(FadeClose);
                    three.startAnimation(FadeClose);
                    two.setClickable(false);
                    three.setClickable(false);
                    isOpen=false;
                }

                else {
                    add_Debit.startAnimation(FadeOpen);
                    add_Credit.startAnimation(FadeOpen);
                    two.startAnimation(FadeOpen);
                    three.startAnimation(FadeOpen);
                    two.setClickable(true);
                    three.setClickable(true);
                    add_Debit.setClickable(true);
                    add_Credit.setClickable(true);
                    isOpen=true;
                }

//                end this section

                Toast.makeText(getApplicationContext(),"Add Credit",Toast.LENGTH_SHORT).show();
            }
        });


//        debit button...

        add_Debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debitDataAdd();

//                For Remove floting button when clicked on debit icon..

                if (isOpen){

                    add_Debit.startAnimation(FadeClose);
                    add_Credit.startAnimation(FadeClose);
                    add_Debit.setClickable(false);
                    add_Credit.setClickable(false);
                    two.startAnimation(FadeClose);
                    three.startAnimation(FadeClose);
                    two.setClickable(false);
                    three.setClickable(false);
                    isOpen=false;
                }

                else {
                    add_Debit.startAnimation(FadeOpen);
                    add_Credit.startAnimation(FadeOpen);
                    two.startAnimation(FadeOpen);
                    three.startAnimation(FadeOpen);
                    two.setClickable(true);
                    three.setClickable(true);
                    add_Debit.setClickable(true);
                    add_Credit.setClickable(true);
                    isOpen=true;
                }

//                end this section

                Toast.makeText(getApplicationContext(),"Add Debit",Toast.LENGTH_LONG).show();

            }
        });
    }


    public void debitDataAdd(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(this);
        LayoutInflater inflater=LayoutInflater.from(this);
        View myview=inflater.inflate(R.layout.add_debit,null);
        mydialog.setView(myview);

        editTextAmmount=(EditText)myview.findViewById(R.id.edittext_Amount);
        editTextTYPE=(EditText)myview.findViewById(R.id.edittext_Type);
        editTextNote=(EditText)myview.findViewById(R.id.edittext_Note);

        final AlertDialog dialog=mydialog.create();
        dialog.setCancelable(false);

        buttonSave=(Button)myview.findViewById(R.id.btnsave);
        buttonCancel=(Button)myview.findViewById(R.id.btncancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ammount_Debit=editTextAmmount.getText().toString().trim();
                type_Debit=editTextTYPE.getText().toString().trim();
                note_Debit=editTextNote.getText().toString().trim();

                if (TextUtils.isEmpty(ammount_Debit)){
                    editTextAmmount.setError("Ammount Required");
                    return;
                }

                int amnt_debit=Integer.parseInt(ammount_Debit);

                if (TextUtils.isEmpty(type_Debit)){
                    editTextTYPE.setError("Type Required");
                    return;
                }

                if (TextUtils.isEmpty(note_Debit)){
                    editTextNote.setError("Note Required");
                    return;
                }

                String id=DebidDatabase.push().getKey();
                Data data=new Data(amnt_debit,id,type_Debit,note_Debit);
                DebidDatabase.child(id).setValue(data);

                //maindatabase.child("DebidDatabase").child(id).setValue(data);

                Toast.makeText(getApplicationContext(),"Data Added..",Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void creditDataADD(){

        AlertDialog.Builder mydialog=new AlertDialog.Builder(this);
        LayoutInflater inflater=LayoutInflater.from(this);
        View myview=inflater.inflate(R.layout.add_credit,null);
        mydialog.setView(myview);

        editTextCredit_Ammount=(EditText)myview.findViewById(R.id.edittext_Amount);
        editTextCredit_TYPE=(EditText)myview.findViewById(R.id.edittext_Type);
        editTextCredit_Note=(EditText)myview.findViewById(R.id.edittext_Note);

        final AlertDialog dialog=mydialog.create();
        dialog.setCancelable(false);
        Credit_buttonSave=(Button)myview.findViewById(R.id.btnsave);
        Credit_buttonCancel=(Button)myview.findViewById(R.id.btncancel);

        Credit_buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ammount_Credit=editTextCredit_Ammount.getText().toString().trim();
                type_Credit=editTextCredit_TYPE.getText().toString().trim();
                note_Credit=editTextCredit_Note.getText().toString().trim();


                if (TextUtils.isEmpty(ammount_Credit)){
                    editTextCredit_Ammount.setError("Ammount Required");
                    return;
                }

                int amm_credit=Integer.parseInt(ammount_Credit);

                if (TextUtils.isEmpty(type_Credit)){
                    editTextCredit_TYPE.setError("Type Required");
                    return;
                }

                if (TextUtils.isEmpty((note_Credit))){
                    editTextCredit_Note.setError("Note Required");
                    return;
                }

                String id=CreditDatabase.push().getKey();
                Data data=new Data(amm_credit,id,type_Credit,note_Credit);
                CreditDatabase.child(id).setValue(data);

                //maindatabase.child("CreditDatabase").child(id).setValue(data);

                Toast.makeText(getApplicationContext(),"Data Added..",Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });

        Credit_buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drwer = (DrawerLayout) findViewById(R.id.drawer_layout_id);

        if (drwer.isDrawerOpen(GravityCompat.END)) {
            drwer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }

    }


    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected


        switch (itemId) {
            case R.id.nav_account:
                fragment=new AccountFragment();
                break;
            case R.id.nav_credit:
                fragment=new CreditBalanceFragment();
                break;

            case R.id.nav_debit:
                fragment=new DebitBalanceFragment();
                break;

            case R.id.nav_about:
                fragment=new AboutFragment();
                break;

            case R.id.nav_sharewithfriend:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareSubText = "Let's enjoy music of Shironamhin";
                String shareBodyText = "http://shironamhin.net/";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(shareIntent, "Share With"));
                break;

            case R.id.nav_profile:
                fragment=new ProfileFragment();
                break;
        }




        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_content, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }


}
