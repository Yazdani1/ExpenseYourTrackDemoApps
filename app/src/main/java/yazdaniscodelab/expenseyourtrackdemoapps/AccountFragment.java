package yazdaniscodelab.expenseyourtrackdemoapps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import yazdaniscodelab.expenseyourtrackdemoapps.Model.Data;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private TextView credit_Result;
    private TextView debit_Result;
    private TextView total_balance;

    private String credit_sum_value;
    private String debit_sum_value;

    private String totalt_String;

    private DatabaseReference creditDatabase;
    private DatabaseReference debitDatabase;
    private DatabaseReference maindatabase;

    private Query query;

    ///int variamble for all ammount..

    int credit_amount_sum;
    int debit_value_sum;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_account, container, false);

        creditDatabase= FirebaseDatabase.getInstance().getReference().child("CreditData");
        debitDatabase=FirebaseDatabase.getInstance().getReference().child("DebitData");

        credit_Result=(TextView)myview.findViewById(R.id.creditresult_Txt);
        debit_Result=(TextView)myview.findViewById(R.id.debittresult_Txt);
        total_balance=(TextView)myview.findViewById(R.id.totalbalanceresult_Txt);


        debitDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                 debit_value_sum=0;

                for (DataSnapshot mysnapshot:dataSnapshot.getChildren()){
                    Data data=mysnapshot.getValue(Data.class);
                    debit_value_sum+=data.getAmmount();

//                    int total=debit_value_sum-credit_amount_sum;
                    debit_sum_value=String.valueOf(debit_value_sum);
                }
                int total=credit_amount_sum-debit_value_sum;
                totalt_String=String.valueOf(total);
                total_balance.setText(totalt_String);

                debit_Result.setText(debit_sum_value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        creditDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                credit_amount_sum=0;

                for (DataSnapshot mysnapshot:dataSnapshot.getChildren()){
                    Data data=mysnapshot.getValue(Data.class);
                    credit_amount_sum+=data.getAmmount();
//                    int total=debit_value_sum+credit_amount_sum;
                    credit_sum_value=String.valueOf(credit_amount_sum);
                }
                int total=credit_amount_sum-debit_value_sum;
                totalt_String=String.valueOf(total);
                total_balance.setText(totalt_String);
                credit_Result.setText(credit_sum_value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return myview;
    }
}
