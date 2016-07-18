package timeTest;

public class Pair<L, R> {

	private final L decription;
	private final R period;

	public Pair(L left, R right) {
		this.decription = left;
		this.period = right;
	}

	public L getDescription() {
		return decription;
	}

	public R getPeriod() {
		return period;
	}

	@Override
	public int hashCode() {
		return decription.hashCode() ^ period.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair))
			return false;
		@SuppressWarnings("rawtypes")
		Pair pairo = (Pair) o;
		return this.decription.equals(pairo.getDescription()) && this.period.equals(pairo.getPeriod());
	}

}