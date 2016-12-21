package co.moonmonkeylabs.realmsearchview.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.moonmonkeylabs.realmsearchview.R;
import co.moonmonkeylabs.realmsearchview.model.Blog;
/**
 * View for a {@link Blog} model.
 */
public class BlogItemView extends RelativeLayout {

        public BlogItemView(Context context) {
        super(context);
        init(context);
    }

    ImageView avatarIv;

    TextView title;

    TextView date;

    TextView description;



    private void init(Context context) {
        View root = inflate(context, R.layout.blog_item_view, this);
        avatarIv = (ImageView) root.findViewById(R.id.avatar);
        title = (TextView) root.findViewById(R.id.title);
        date = (TextView) root.findViewById(R.id.date);
        description = (TextView) root.findViewById(R.id.description);
        //ButterKnife.bind(this);
    }

    public void bind(Blog blog) {
        Picasso.with(getContext())
                .load(blog.getImage())
                .resize(200, 200)
                .transform(new RoundedTransformation(100, 4))
                .into(avatarIv);
        //emoji.setText(blog.getEmoji());
        title.setText(blog.getTitle());
        date.setText(blog.getDate());
        description.setText(blog.getContent());
    }
}
