package info.androidhive.materialdesign.tabsdata;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.androidhive.materialdesign.R;

/**
 * Created by akrishnan on 23-12-2015.
 */
public class MessageDialogFragment extends DialogFragment implements View.OnClickListener{

    private Button messageOk;
    private TextView rememberMe,forgetPassword;
    private EditText username,password;
    private Integer messageId;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.message_ok:
                if (getActivity() != null) {
                    dismiss();
                }
                break;
            case R.id.remember_me:
                break;
            case R.id.forget_password:
                break;
            case R.id.username:
                break;
            case R.id.password:
                break;

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.message_dialogfragment,
                container, false);
        this.messageOk = (Button) view.findViewById(R.id.message_ok);
        this.username=(EditText)view.findViewById(R.id.username);
        this.password=(EditText)view.findViewById(R.id.password);
        this.rememberMe=(TextView)view.findViewById(R.id.remember_me);
        this.forgetPassword=(TextView)view.findViewById(R.id.forget_password);
        messageId = getArguments().getInt("msgid");
        this.messageOk.setOnClickListener(this);
        return  view;
    }
    static public MessageDialogFragment newInstance( int id) {
        MessageDialogFragment msg = new MessageDialogFragment();
        // Supply num input as an argument.
        Bundle bundle = new Bundle();
        bundle.putInt("msgid", id);
        msg.setArguments(bundle);
        return msg;
    }
}
