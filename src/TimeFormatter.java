import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// long 타입의 밀리초 값을 받아서 이를 시각 문자열(시:분:초.밀리초 혹은 시:분:초)로 바꿔주는 역할
public class TimeFormatter {
    // sdf는 출력 시 “시:분:초.밀리초” 형태로 포맷팅하는 객체
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
    private static final SimpleDateFormat sdfNoMillis = new SimpleDateFormat("HH:mm:ss");

    // 클래스가 처음 로딩될 때 한 번만 실행
    static {
        // 두 포맷터의 타임존을 모두 GMT(=UTC)로 설정
        // “밀리초(ms) → 시:분:초(.밀리초)” 포맷을 할 때, 불필요한 시차(off­set)가 개입되지 않도록 하기 위함
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdfNoMillis.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String format(long millis) {
        // ex) TimeFormatter.format(3600_000) → "01:00:00.000"
        return sdf.format(new Date(millis));
    }

    public static String formatNomillis(long millis) {
        return sdfNoMillis.format(new Date(millis));
    }
}
