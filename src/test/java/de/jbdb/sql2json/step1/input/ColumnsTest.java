package de.jbdb.sql2json.step1.input;

import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_COLUMN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class ColumnsTest {

	@Test
	public void constructionWithOneSimpleEntry() throws Exception {
		Columns columns = new Columns(TEST_COLUMN);

		assertColumns(columns, 1);
	}

	@Test
	public void constructionWithMultipleEntries() throws Exception {
		Columns columns = new Columns(TEST_COLUMN + "," + TEST_COLUMN);

		assertColumns(columns, 2);
	}

	@Test
	public void constructionWithTrailingBraces() throws Exception {
		Columns columns = new Columns(TEST_COLUMN + "," + TEST_COLUMN + ")");

		assertColumns(columns, 2);
	}

	@Test
	public void constructionWithSingleQuotes() throws Exception {
		Columns columns = new Columns("'" + TEST_COLUMN + "'");

		assertColumns(columns, 1);
	}

	@Test
	public void constructionWithDoubleQuotes() throws Exception {
		Columns columns = new Columns("\"" + TEST_COLUMN + "\"");

		assertColumns(columns, 1);
	}

	@Test
	public void constructionWithBackticks() throws Exception {
		Columns columns = new Columns("`" + TEST_COLUMN + "`");

		assertColumns(columns, 1);
	}

	@Test
	public void constructionWithWhitespace() throws Exception {
		Columns columns = new Columns("    " + TEST_COLUMN + " \t, \t" + TEST_COLUMN + "      ");

		assertColumns(columns, 2);
	}

	private void assertColumns(Columns columns, int size) {
		List<ColumnName> names = columns.getNames();

		assertThat(names).isNotNull();
		assertThat(names).isNotEmpty();
		assertThat(names).hasSize(size);

		for (int i = 0; i < size; i++) {
			assertThat(names.get(i)).isEqualTo(TEST_COLUMN);
		}
	}
}