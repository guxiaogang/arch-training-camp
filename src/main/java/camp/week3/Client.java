package camp.week3;

public class Client {

    public static void main(String[] args) {
        Component winForm = new WinForm("Window窗口");

        Picture pic = new Picture("LOGO图片");

        winForm.add(pic);
        winForm.add(new Button("登录"));
        winForm.add(new Button("注册"));

        Frame frame = new Frame("FRAME1");
        frame.add(new Label("用户名"));
        frame.add(new TextBox("文本框"));

        frame.add(new Label("密码"));
        frame.add(new PasswordBox("密码框"));

        frame.add(new CheckBox("复选框"));
        frame.add(new TextBox("记住用户名"));
        frame.add(new LinkLabel("忘记密码"));

        winForm.add(frame);
        winForm.print();
    }
}
