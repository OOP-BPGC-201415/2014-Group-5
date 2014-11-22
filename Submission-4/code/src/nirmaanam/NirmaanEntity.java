package nirmaanam;
import java.sql.SQLException;

public interface NirmaanEntity{
	public NirmaanEntity load(int entityId) throws SQLException, EntityNotFoundException;
	public void store() throws SQLException, IncompleteFieldException;
}