package hyf.model.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JDBC.JDBCUtil;
@Service
public class BaseFacade {

	@Autowired
	protected JDBCUtil Dao ;
}
