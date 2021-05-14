package utils;

import pages.search.SearchPage;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Helpers {
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    public static boolean Same(List<SearchPage.SearchItem> list1, List<SearchPage.SearchItem> list2){
        if(list1.size()!=list2.size())
            return false;

        for(int i=0; i < list1.size();i++){
            if(!list1.get(i)
                    .equals(list2.get(i))){
                return false;
            }
        }

        return true;
    }

    public static int randomNumber(int min, int max){
        Random random = new Random();
        int difference = max-min;
        return random.nextInt(difference)+min;
    }
}
