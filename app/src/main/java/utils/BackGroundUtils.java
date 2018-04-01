package utils;

import com.lfz.lfzmjy.R;

import java.util.Random;

/**
 * Created by LFZ on 2017/12/4.
 * 背景选择器
 */

public class BackGroundUtils {
    private static int images[] = new int[]{R.drawable.backgroundone,R.drawable.backgroundtwo, R.drawable.background3, R.drawable.background4, R.drawable.background, R.drawable.background5};
    public static int getBackgroundId(){
        Random random = new Random();
        int max = images.length - 1;
        int s = random.nextInt(max+2)%(max-0+1) + 0;
        return images[s];
    }
}
