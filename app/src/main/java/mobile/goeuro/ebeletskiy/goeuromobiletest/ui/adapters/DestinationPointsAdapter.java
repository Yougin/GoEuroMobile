package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public class DestinationPointsAdapter extends ArrayAdapter<DestinationPoint> {

  private LayoutInflater inflater;
  private ArrayList<DestinationPoint> destinationPoints;
  private ArrayList<DestinationPoint> suggestions;
  private ArrayList<DestinationPoint> allDestinationPoints;

  @Inject public DestinationPointsAdapter(App context, LayoutInflater inflater) {
    super(context, 0);
    this.inflater = inflater;
    suggestions = new ArrayList<>();
  }

  public void setData(@NotNull List<DestinationPoint> destinationPoints) {
    this.destinationPoints = (ArrayList<DestinationPoint>) destinationPoints;
    allDestinationPoints = (ArrayList<DestinationPoint>) this.destinationPoints.clone();
  }

  @Override public int getCount() {
    return destinationPoints.size();
  }

  @Override public DestinationPoint getItem(int position) {
    return destinationPoints.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    TextView view;
    if (convertView == null) {
      view = (TextView) inflater.inflate(R.layout.view_adapter_destination_points, null);
    } else {
      view = (TextView) convertView;
    }

    view.setText(destinationPoints.get(position).getFullName());

    return view;
  }

  @Override public Filter getFilter() {
    return mFilter;
  }

  Filter mFilter = new Filter() {

    @Override public CharSequence convertResultToString(Object resultValue) {
      return ((DestinationPoint) resultValue).getFullName();
    }

    @Override protected FilterResults performFiltering(CharSequence constraint) {
      if (constraint != null) {
        suggestions.clear();
        for (DestinationPoint point : allDestinationPoints) {
          if (point.getFullName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
            suggestions.add(point);
          }
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = suggestions;
        filterResults.count = suggestions.size();
        return filterResults;
      } else {
        return new FilterResults();
      }
    }

    @Override protected void publishResults(CharSequence constraint, FilterResults results) {
      ArrayList<DestinationPoint> filteredList = (ArrayList<DestinationPoint>) results.values;
      if (results != null && results.count > 0) {
        clear();
        for (DestinationPoint c : filteredList) {
          add(c);
        }
        notifyDataSetChanged();
      }
    }
  };
}

