package com.example.quickrepair.view.Customer.RepairRequests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickrepair.R;
import com.example.quickrepair.domain.RepairRequest;
import com.example.quickrepair.view.Customer.RepairRequests.ItemSelectionListener;
import com.example.quickrepair.view.Customer.RepairRequests.RepairRequestAdapter;

import java.util.ArrayList;

public class CustomerCompletedRepairRequests extends Fragment implements ItemSelectionListener<RepairRequest>
{

        public static final RepairRequest.Status status = RepairRequest.Status.COMPLETED;

        CustomerRepairRequestsActivity activity;
        RecyclerView recyclerView;
        private RepairRequestAdapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;


        //new instance of this fragment
        public static CustomerCompletedRepairRequests newInstance() {
            return new CustomerCompletedRepairRequests();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.customer_completed_repair_requests, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = ((CustomerRepairRequestsActivity) getActivity());

        //get unconfirmed repair requests for this technician
        ArrayList<RepairRequest> repairRequests = activity.getViewModel().getPresenter().searchRepairRequests(activity.getCustomerID(), status);

        //update UI
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view_completed);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RepairRequestAdapter(new ArrayList<RepairRequest>(repairRequests));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setRepairRequestSelectionListener(this);
    }

        /**
         * The method will be called by the adapter, whenever the user clicks on a list item
         * @param item Repair Request
         */
        @Override
        public void onItemSelected(RepairRequest item) {
        activity.getViewModel().getPresenter().onRepairRequestSelectedCompleted(item);
    }
}
