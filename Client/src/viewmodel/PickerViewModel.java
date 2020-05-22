package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ClientModel;
import model.Item;
import model.Job;
import view.picker.PickerTableRowData;

import java.rmi.RemoteException;

public class PickerViewModel {
    private ClientModel model;
    private Job job;

    public PickerViewModel(ClientModel model) { this.model = model; }

    public Job getNewJob() throws RemoteException { return model.getNewJob(); }

    public void logOut() { model.logOut(); }

    public void completeJob(String jobId) throws RemoteException {
        model.completeJob(this.job);
    }

    // I have doubts if this will work
    public ObservableList<PickerTableRowData> getPickerList(){
        ObservableList<PickerTableRowData> list = FXCollections.observableArrayList();

        this.job = model.getNewJob();
        if(job == null){
            System.out.println("there are probably no jobs");
            //todo show the message to the user as well
        }

        for (Item item:  job.getItems()) {
            list.add(new PickerTableRowData(item));
        }
        return list;
    }
}
