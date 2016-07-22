package com.wangpin.ice.sqlit_demo.layer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpi on 7/22/2016.
 */
public class SelectionHelper<T> extends ArrayList {
    public String join(){
        String joinStr = " and ";
        StringBuffer sb = new StringBuffer();
        int size = size();

        for(int i = 0; i < size; i++){
            sb.append(get(i));
            if(i!=size-1){
                sb.append(joinStr);
            }
        }
        return sb.toString();
    }

}
