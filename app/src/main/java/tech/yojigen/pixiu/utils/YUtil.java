package tech.yojigen.pixiu.utils;

import android.content.Context;
import android.util.Log;

public class YUtil {
    private static Context mApplicationContext;

    public static void init(Context context) {
        mApplicationContext = context.getApplicationContext();
        Log.d("YUtil", " \n" +
                "                                                                                \n" +
                "YYYYYYY       YYYYYYYUUUUUUUU     UUUUUUUU        tttt            iiii  lllllll \n" +
                "Y:::::Y       Y:::::YU::::::U     U::::::U     ttt:::t           i::::i l:::::l \n" +
                "Y:::::Y       Y:::::YU::::::U     U::::::U     t:::::t            iiii  l:::::l \n" +
                "Y::::::Y     Y::::::YUU:::::U     U:::::UU     t:::::t                  l:::::l \n" +
                "YYY:::::Y   Y:::::YYY U:::::U     U:::::Uttttttt:::::ttttttt    iiiiiii  l::::l \n" +
                "   Y:::::Y Y:::::Y    U:::::D     D:::::Ut:::::::::::::::::t    i:::::i  l::::l \n" +
                "    Y:::::Y:::::Y     U:::::D     D:::::Ut:::::::::::::::::t     i::::i  l::::l \n" +
                "     Y:::::::::Y      U:::::D     D:::::Utttttt:::::::tttttt     i::::i  l::::l \n" +
                "      Y:::::::Y       U:::::D     D:::::U      t:::::t           i::::i  l::::l \n" +
                "       Y:::::Y        U:::::D     D:::::U      t:::::t           i::::i  l::::l \n" +
                "       Y:::::Y        U:::::D     D:::::U      t:::::t           i::::i  l::::l \n" +
                "       Y:::::Y        U::::::U   U::::::U      t:::::t    tttttt i::::i  l::::l \n" +
                "       Y:::::Y        U:::::::UUU:::::::U      t::::::tttt:::::ti::::::il::::::l\n" +
                "    YYYY:::::YYYY      UU:::::::::::::UU       tt::::::::::::::ti::::::il::::::l\n" +
                "    Y:::::::::::Y        UU:::::::::UU           tt:::::::::::tti::::::il::::::l\n" +
                "    YYYYYYYYYYYYY          UUUUUUUUU               ttttttttttt  iiiiiiiillllllll\n" +
                "                                                                                ");
    }

    public static Context getApplicationContext() {
        return mApplicationContext;
    }
}
