package com.integrals.chordlinesapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ImageView;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.integrals.chordlinesapp.Helper.FirebaseActions;
import com.integrals.chordlinesapp.Helper.YoutubeActions;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import io.github.tonnyl.light.Light;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseActions firebaseActions;
    private DatabaseReference databaseReferenceCreateAlbum;
    private DatabaseReference databaseReferenceMajor;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReferenceAlbumList;
    private Activity activity;
    private SparkButton sparkButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activity=this;
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser==null){
            finish();
            startActivity(new Intent(MainActivity.this,SignupActivity.class));

        }



        firebaseActions=new FirebaseActions(getApplicationContext(),activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_open);
        toolbar.setSubtitle("Please wait..");
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.albumListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        databaseReferenceCreateAlbum=FirebaseDatabase.getInstance().getReference().child("Posts");
        databaseReferenceMajor=FirebaseDatabase.getInstance().getReference();
        databaseReferenceAlbumList=FirebaseDatabase.getInstance().getReference().child("Posts");
        sparkButton=(SparkButton)findViewById(R.id.spark_button);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseActions.loadRecyclerView(databaseReferenceAlbumList,recyclerView);
        Light.info(recyclerView, "For our music services", Light.LENGTH_LONG)

                .setActionTextColor(Color.WHITE)
                .setAction("CONTACT", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showContactDialogue("");
                    }
                }).show();

            sparkButton.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {

               firebaseActions.addLike(databaseReferenceMajor);
                      } else {
                firebaseActions.removeLike(databaseReferenceMajor);
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();


        databaseReferenceMajor.child("Likes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toolbar.setSubtitle(dataSnapshot.getValue().toString().trim()+" likes__");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseActions.createAlbum(databaseReferenceCreateAlbum);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
         if (id == R.id.nav_invite) {
             Snackbar.with(activity,null)
                     .type(Type.CUSTOM)
                     .message("Loading App invite  link...")
                     .duration(Duration.SHORT)
                     .fillParent(true)
                     .textAlign(Align.LEFT)
                     .show();
             firebaseActions.sentAppInviteLink(databaseReferenceMajor);

        } else if (id == R.id.nav_contact) {
             showContactDialogue("Devoloper E-mail: integrals.athulkrishna@gmail.com");
          } else if(id == R.id.nav_subscribe){
             YoutubeActions youtubeActions= new YoutubeActions(getApplicationContext(),activity);
             youtubeActions.subscribeYoutubeChannel("UCvo6q3_ZBqUuJZ-g_eak1-Q");


         }

        return true;
    }



    private void showContactDialogue(String d){
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
                .setContentImageDrawable(R.drawable.buisness_card)
                .setDialogBackgroundColor(getResources().getColor(R.color.cfdialoguecolorContact))
                .setMessage(" "+d)
                .addButton(" SHARE CONTACT ", -1, Color.BLUE, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, (dialog, which) -> {
                    dialog.dismiss();
                    Snackbar.with(activity,null)
                            .type(Type.CUSTOM)
                            .message("Loading Buisness card..")
                            .duration(Duration.SHORT)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
                    dialog.dismiss();
                    firebaseActions.shareImage();
                })
                .addButton("SHARE APP",-1,Color.BLUE,CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, (dialog, which) -> {
                    firebaseActions.sentAppInviteLink(databaseReferenceMajor);
                })
                .addButton("  OK  ", -1, Color.GRAY, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, (dialog, which) -> {

                    dialog.dismiss();
                });


        builder.show();

    }

}
