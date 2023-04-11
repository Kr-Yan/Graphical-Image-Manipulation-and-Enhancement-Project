public interface IView {

  String getFileName();

  void setListener(ControlView controlView);

  void display();
}
