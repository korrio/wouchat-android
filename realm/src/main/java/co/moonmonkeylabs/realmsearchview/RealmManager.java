package co.moonmonkeylabs.realmsearchview;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.moonmonkeylabs.realmsearchview.model.Blog;
import co.moonmonkeylabs.realmsearchview.model.TheMessageObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by korrio on 4/17/16.
 */
public class RealmManager {

    private static RealmManager singleton;

    private static Realm realm;

    public static RealmManager getInstance() {
        if (singleton == null) {
            singleton = new RealmManager();
        }
        return singleton;
    }

    private Context context;

    public Realm initRealm(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();

        return Realm.getInstance(realmConfig);
    }

    public void resetRealm(Context context) {
        this.context = context;
        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.deleteRealm(realmConfig);
    }

    public void loadDataToRealm(Realm realm, List<TheMessageObject> messageObjectList) {
        SimpleDateFormat formatIn = new SimpleDateFormat("MMMM d, yyyy");
        SimpleDateFormat formatOut = new SimpleDateFormat("MM/d/yy");
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonFactory jsonFactory = new JsonFactory();
        Random random = new Random();
        try {
//            JsonParser jsonParserBlog =
//                    jsonFactory.createParser(getResources().openRawResource(R.raw.blog));
//            List<Blog> entries =
//                    objectMapper.readValue(jsonParserBlog, new TypeReference<List<Blog>>() {
//                    });
//
//            JsonParser jsonParserEmoji =
//                    jsonFactory.createParser(getResources().openRawResource(R.raw.emoji));
//            List<String> emojies =
//                    objectMapper.readValue(jsonParserEmoji, new TypeReference<List<String>>() {});
//
//            int numEmoji = emojies.size();
//            for (Blog blog : entries) {
//                blog.setEmoji(emojies.get(random.nextInt(numEmoji)));
//                try {
//                    blog.setDate(formatOut.format(formatIn.parse(blog.getDate())));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }

            List<Blog> entries = new ArrayList<>();
            for(TheMessageObject msgObj : messageObjectList) {
                Blog blog = new Blog();
                blog.setId(msgObj.getId());
                blog.setCid(msgObj.getConversationId());
                blog.setImage(msgObj.getAvatarUrl());
                blog.setTitle("@" + msgObj.getSender().getUsername());
                blog.setContent(msgObj.getMessage());
                if(msgObj.getMessageType() == 0)
                    entries.add(blog);
            }

            realm.beginTransaction();
            realm.copyToRealm(entries);
            realm.commitTransaction();
            realm.close();

        } catch (Exception e) {
            throw new IllegalStateException("Could not load blog data.");
        }
    }

}
