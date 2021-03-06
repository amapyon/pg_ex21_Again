package step10;

public class MorningService  extends TimeService {

	@Override
	public boolean isServiceTime(int startHour) {
		if(isJoined()){
			 //割引対象期間6：00-7：59
			if (6 <= startHour && startHour <= 7) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getServiceCode() {
		return "E3";
	}

	@Override
	public int getDiscount() {
		return 10;
	}

	@Override
	public int getBasicCharge() {
		return 200;
	}

}
