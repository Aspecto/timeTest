package time_test;

public class Pair<L,R> {

	  private final L decription;
	  private final R duration;

	  public Pair(L left, R right) {
	    this.decription = left;
	    this.duration = right;
	  }

	  public L getDescription() { return decription; }
	  public R getTime() { return duration; }

	  @Override
	  public int hashCode() { return decription.hashCode() ^ duration.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (!(o instanceof Pair)) return false;
	    @SuppressWarnings("rawtypes")
		Pair pairo = (Pair) o;
	    return this.decription.equals(pairo.getDescription()) &&
	           this.duration.equals(pairo.getTime());
	  }

	}