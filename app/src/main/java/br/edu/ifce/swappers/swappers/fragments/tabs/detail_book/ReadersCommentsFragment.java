package br.edu.ifce.swappers.swappers.fragments.tabs.detail_book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifce.swappers.swappers.MockSingleton;
import br.edu.ifce.swappers.swappers.R;
import br.edu.ifce.swappers.swappers.activities.DetailBookActivity;
import br.edu.ifce.swappers.swappers.adapters.CommentRecyclerViewAdapter;
import br.edu.ifce.swappers.swappers.model.Comment;
import br.edu.ifce.swappers.swappers.model.Review;
import br.edu.ifce.swappers.swappers.util.RecycleViewOnClickListenerHack;
import br.edu.ifce.swappers.swappers.util.RetrieveReviewsTaskInterface;
import br.edu.ifce.swappers.swappers.util.SwappersToast;
import br.edu.ifce.swappers.swappers.webservice.RetrieveReviewsTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadersCommentsFragment extends Fragment implements RecycleViewOnClickListenerHack, RetrieveReviewsTaskInterface {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Review> dataSource;
    CommentRecyclerViewAdapter adapter;

    public ReadersCommentsFragment() {
        dataSource = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RetrieveReviewsTask task = new RetrieveReviewsTask(getActivity(), this);
        DetailBookActivity detailBookActivity = (DetailBookActivity) getActivity();

        task.execute(detailBookActivity.getBook().getId());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_readers_comments, container, false);

        adapter = new CommentRecyclerViewAdapter(dataSource);
        adapter.setRecycleViewOnClickListenerHack(this);

        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.readers_comments_list);


        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


    @Override
    public void onClickListener(View view, int position) {
        //Implementar na versão 2 do Swappers.

    }

    @Override
    public void onReceiveReviews(ArrayList<Review> reviews) {
        if (reviews != null) {
            this.dataSource.clear();
            this.dataSource.addAll(reviews);
            this.adapter.notifyDataSetChanged();
        }
        else {
            SwappersToast.makeText(getActivity(), "Seja o primeiro a comentar!", Toast.LENGTH_LONG).show();
        }
    }
}
