package yazdaniscodelab.expenseyourtrackdemoapps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import yazdaniscodelab.expenseyourtrackdemoapps.Model.Data;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebitBalanceFragment extends Fragment {


    private RecyclerView recyclerView;
    private DatabaseReference databasecredit_ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View dbtview=inflater.inflate(R.layout.fragment_debit_balance, container, false);

        databasecredit_ref= FirebaseDatabase.getInstance().getReference().child("DebitData");
        databasecredit_ref.keepSynced(true);
        recyclerView=(RecyclerView)dbtview.findViewById(R.id.recyclercredit_xml);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        return dbtview;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CreditBalanceFragment.Myviewholder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Data, CreditBalanceFragment.Myviewholder>
                (
                        Data.class,
                        R.layout.iteam_data,
                        CreditBalanceFragment.Myviewholder.class,
                        databasecredit_ref
                ) {
            @Override
            protected void populateViewHolder(CreditBalanceFragment.Myviewholder viewHolder, Data model, int position) {

                final String post_key=getRef(position).getKey();

                viewHolder.setAmmount(model.getAmmount());
                viewHolder.setType(model.getType());
                viewHolder.setDescription(model.getNote());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),post_key,Toast.LENGTH_LONG).show();
                    }
                });


            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class Myviewholder extends RecyclerView.ViewHolder{

        View mview;

        public Myviewholder(View itemView) {
            super(itemView);
            mview=itemView;
        }


        public void setAmmount(int ammount){
            TextView mammount=mview.findViewById(R.id.ammount_xml);

            String stmount=String.valueOf(ammount);
            mammount.setText(stmount);
        }

        public void setType(String type){
            TextView mtype=mview.findViewById(R.id.type_xml);
            mtype.setText(type);
        }

        public void setDescription(String description){
            TextView mdescription=mview.findViewById(R.id.description_xml);
            mdescription.setText(description);
        }

    }



}
