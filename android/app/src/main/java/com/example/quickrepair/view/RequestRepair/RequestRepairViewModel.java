package com.example.quickrepair.view.RequestRepair;

import androidx.lifecycle.ViewModel;

import com.example.quickrepair.dao.Initializer;
import com.example.quickrepair.memorydao.CustomerDAOMemory;
import com.example.quickrepair.memorydao.JobTypeDAOMemory;
import com.example.quickrepair.memorydao.MemoryInitializer;
import com.example.quickrepair.memorydao.RepairRequestDAOMemory;
import com.example.quickrepair.memorydao.TechnicianDAOMemory;

public class RequestRepairViewModel extends ViewModel {

    RequestRepairPresenter presenter;

    public RequestRepairViewModel() {

        Initializer initializer= new MemoryInitializer();
        initializer.prepareData();

        presenter = new RequestRepairPresenter(new TechnicianDAOMemory(), new JobTypeDAOMemory()
                , new CustomerDAOMemory(), new RepairRequestDAOMemory());

    }


    public RequestRepairPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // clear view instance to avoid leaking activity , i dont understand what i am supposed to do
        //TODO
        //presenter.clearView();
    }
}
