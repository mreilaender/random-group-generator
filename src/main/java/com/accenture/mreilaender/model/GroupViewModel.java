package com.accenture.mreilaender.model;

import com.accenture.mreilaender.model.tabPane.PersonTableModel;
import com.accenture.mreilaender.view.GroupView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;

import java.util.HashMap;

/**
 * @author manuel
 * @version 11/21/16
 */
public class GroupViewModel implements ViewModel {
    private HashMap<Integer, PersonTableModel> tableModels;
    private HashMap<Integer, ViewTuple<GroupView, GroupViewModel>> views;

    public GroupViewModel() {
        tableModels = new HashMap<>();
        views = new HashMap<>();
    }


}
