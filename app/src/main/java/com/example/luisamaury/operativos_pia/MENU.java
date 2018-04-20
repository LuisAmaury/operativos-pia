package com.example.luisamaury.operativos_pia;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MENU extends AppCompatActivity {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToogle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        FragmentManager fragmentManager =  getSupportFragmentManager();

        switch (menuItem.getItemId()) {
            case R.id.Horario:
                fragmentClass = I_HORARIO.class;
                break;
            case R.id.Inscripcion:
                fragmentClass = I_INSCRIPCION.class;
                break;
            case R.id.Alumno:
                fragmentClass = I_ALUMNO.class;
                break;
            case R.id.Materia:
                fragmentClass = I_MATERIA.class;
                break;
            case R.id.Group:
                fragmentClass = I_GRUPO.class;
                break;
            case R.id.Usuario:
                fragmentClass = I_USUARIO.class;
                break;
            default:
                fragmentClass = I_HORARIO.class;
        }

        try {
           fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerlayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
