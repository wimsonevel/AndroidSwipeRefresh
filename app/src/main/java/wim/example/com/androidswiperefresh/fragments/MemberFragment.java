package wim.example.com.androidswiperefresh.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wim.example.com.androidswiperefresh.R;
import wim.example.com.androidswiperefresh.adapter.MemberListAdapter;
import wim.example.com.androidswiperefresh.model.Member;

/**
 * Created by docotel on 3/30/16.
 */
public class MemberFragment extends Fragment {

    private RecyclerView listMember;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private MemberListAdapter memberListAdapter;

    protected Context context;

    public static MemberFragment newInstance(){
        return new MemberFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member, container, false);

        listMember = (RecyclerView) rootView.findViewById(R.id.listMember);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter();

        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);

        loadData();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                memberListAdapter.clear();
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

    }

    private void loadData(){
        List<Member> memberList = new ArrayList<>();
        Member member;

        int thumb[] = {R.drawable.ayana_shahab, R.drawable.beby_chaseara_anadila, R.drawable.delima_rizky,
                R.drawable.dena_siti_rohyati, R.drawable.elaine_hartanto, R.drawable.frieska_anastasia_laksani,
                R.drawable.gabriella, R.drawable.ghaida, R.drawable.jennifer_rachel_natasya,
                R.drawable.jessica_vania_widjaja, R.drawable.jessica_veranda, R.drawable.melody_nurramdhani_laksani,
                R.drawable.nabilah_ratna_ayu, R.drawable.rezky_wiranti_dhike, R.drawable.sendy_ariani,
                R.drawable.shania_junianantha, R.drawable.shinta_naomi, R.drawable.sofia_meifaliani,
                R.drawable.sonia_natalia, R.drawable.thalia_ivanka_elizabeth_frederik};

        String name[] = {"Ayana Shahab", "Beby Chaesara Anadila", "Delima Rizky", "Dena Siti Rohyati",
                "Elaine Hartanto", "Frieska Anastasia Laksani", "Gabriella M. W.", "Ghaida Farisya",
                "Jennifer Rachel Natasya", "Jessica Vania Widjaja", "Jessica Veranda", "Melody Nurramdhani Laksani",
                "Nabilah Ratna Ayu Azalia", "Rezky Wiranti Dhike", "Sendy Ariani", "Shania Junianatha",
                "Shinta Naomi", "Sofia Meifaliani", "Sonia Natalia", "Thalia Ivanka Elizabeth"};

        String team = "Team J";

        for(int i=0; i < thumb.length; i++){
            member = new Member();

            member.setId(i+1);
            member.setName(name[i]);
            member.setTeam(team);
            member.setThumb(thumb[i]);

            memberList.add(member);
        }

        memberListAdapter.addAll(memberList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
