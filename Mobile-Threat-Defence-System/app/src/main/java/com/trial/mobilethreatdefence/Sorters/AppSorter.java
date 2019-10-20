package com.trial.mobilethreatdefence.Sorters;
import com.trial.mobilethreatdefence.Models.AppListModel;

import java.util.Comparator;

public class AppSorter implements Comparator<AppListModel>
{
    @Override
    public int compare(AppListModel o1, AppListModel o2) {
        return o1.getAppName().compareToIgnoreCase(o1.getAppName());
    }
}
