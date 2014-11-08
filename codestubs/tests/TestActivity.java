//import java.sql.*;

class TestActivity{
	
	public void setUp(){
	}
	
	public void tearDown(){
	}
	
	
	public void testGetAttendees(){
		ResultSet RSMock = createNiceMock(ResultSet.class);
		
		expect(RSMock.getInt("id")).andReturn(123);
		expect(RSMock.getString("name")).andReturn("abc");
		expect(RSMock.getString("bitsId")).andReturn("YYYYBBBBIIIC");
		
		replay(RSMock);
		Database.setMock( RSMock );
		
		Activity activity = new Activity("testActivity","description",new Volunteer("head","headId",2012));
		//Setup a new dummy Volunteer
		
		Volunteer[] aList = activity.getAttendees();
		assertEquals("getAttendees failed", aList[0].getId(), 123);
		
	}
	
	public void testAddAttendee(){
		Activity activity = new Activity("testActivity","description",new Volunteer("head","headId",2012));
		//Setup a new dummy Volunteer
		Volunteer invited = new Volunteer("abc","YYYYBBBBIIIC", 2012);
		invited.setId(1);
		
		activity.addAttendee(invited);
		Volunteer[] aList = activity.getAttendees();
		assertEquals("AddAttendee failed", aList[aList.size()-1].getId(), invited.getId());
	}
	
	public void testGetAvailability(){
		ResultSet RSMock = createNiceMock(ResultSet.class);
		
		expect(RSMock.getInt("available")).andReturn(1);
		expect(RSMock.getInt("available")).andReturn(2);
		expect(RSMock.getInt("available")).andReturn(0);
		replay(RSMock);
		
		Database.setMock( RSMock );
		
		Activity activity = new Activity("testActivity","description",new Volunteer("head","headId",2012));
		Volunteer v1 = new Volunteer("abc","YYYYBBBBIIIC", 2012);
		v1.setId(1);
		
		assertEquals("Expected 1, Didn\'t get",activity.getAvailability(v1), 1);
		assertEquals("Expected 1, Didn\'t get",activity.getAvailability(v1), 2);
		assertEquals("Expected 1, Didn\'t get",activity.getAvailability(v1), 0);
		
	}
	
	public void testConfirmAvailability(){
			Activity activity = new Activity("testActivity","description",new Volunteer("head","headId",2012));
			//Setup a new dummy Volunteer
			Volunteer v1 = new Volunteer("abc","YYYYBBBBIIIIC", 2012);
			v1.setId(1);
			Volunteer v2 = new Volunteer("pqr","YYYYBBBBIIIIC", 2012);
			v1.setId(2);
			
			subject.confirmAvailability(v1, true);
			assertEquals("Confirm availability ( true ) failed ", subject.getAvailability(v1), true);
			
			subject.confirmAvailability(v2, false);
			assertEquals("Confirm availability ( true ) failed ", subject.getAvailability(v2), false);
	}
}