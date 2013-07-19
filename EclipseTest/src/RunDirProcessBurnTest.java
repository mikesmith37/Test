

import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;

/**
 * Perform a Create Analysis Step, where each step is created in its own unique tree
 */

public class RunDirProcessBurnTest extends TestCase{

	public static final int NUMBER_OF_STEPS = 2000;

	public static final long PREFERRED_TIME = 4000 * NUMBER_OF_STEPS;//Create and Save (two txns)

	public static final long MAX_TIME = 5000 * NUMBER_OF_STEPS;

	public static final int AVERAGE_USERS = 5;

	public static final int PEAK_USERS = 10;
	
	public RunDirProcessBurnTest( String s ){
		super(s);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTest(timedRepeatedTest());
//		suite.addTest(timedTest());
//		suite.addTest(timedLoadTestAverage());
//		suite.addTest(timedLoadTestPeak());


		return suite;
	}

	protected static Test getWrappedTest() {
		return new RunProcessTest("testRunDirTmp");
	}

	protected static Test timedTest() {

		Test test = getWrappedTest();

		Test timed = new TimedTest(test, PREFERRED_TIME);

		return timed;
	}

	protected static Test timedLoadTestAverage() {

		Test test = getWrappedTest();

		Test timed = new TimedTest(test, MAX_TIME);

		Test loadTest = new LoadTest(timed, AVERAGE_USERS);

		return loadTest;
	}
	
	protected static Test timedRepeatedTest() {

			Test test = getWrappedTest();

			Test timed = new TimedTest(test, MAX_TIME);

			Test rt = new RepeatedTest(timed , NUMBER_OF_STEPS );

			return rt;
	}

	protected static Test timedLoadTestPeak() {

		Test test = getWrappedTest();

		Test timed = new TimedTest(test, MAX_TIME);

		Test loadTest = new LoadTest(timed, PEAK_USERS);

		return loadTest;
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}
	

}


