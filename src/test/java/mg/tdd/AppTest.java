/**
 * 
 */
package mg.tdd;


import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;


import java.util.List;

import org.junit.Test;

/**
 * @author Morten G.
 * 
 */
public class AppTest {

	@Test
	public void shouldDoSomethingSmart() {
		@SuppressWarnings("rawtypes")
		List mock = mock(List.class);
		
		when(mock.get(anyInt())).thenReturn("element");
		assertThat(new Boolean(true)).isNotNull();
	}

}
