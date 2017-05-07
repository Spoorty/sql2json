package de.jbdb.sql2json.step1.input;

import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_VALUE1;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class RowsTest {

	// empty string = error
	// null = error

	@Test
	public void twoValuesInTwoRowsEach() throws Exception {
		Rows rows = new Rows(
				"(" + TEST_VALUE1 + "," + TEST_VALUE1 + ")," + "(" + TEST_VALUE1 + "," + TEST_VALUE1 + ")");

		assertRows(rows, 2, 2);
	}

	@Test
	public void twoValuesInTwoRowsEachWithWhitespace() throws Exception {
		Rows rows = new Rows("   (   " + TEST_VALUE1 + " ,  " + TEST_VALUE1 + "   )  ,    " + " (  " + TEST_VALUE1
				+ "   , " + TEST_VALUE1 + "   ) ");

		assertRows(rows, 2, 2);
	}

	// TODO assertAddAll

	private void assertRows(Rows rows, int numberOfRows, int numberOfValues) {
		List<Row> rowAsList = rows.asList();
		assertThat(rowAsList).isNotNull();
		assertThat(rowAsList).isNotEmpty();
		assertThat(rowAsList).hasSize(numberOfRows);
		assertThat(rowAsList.get(0)).isEqualTo(createRow(numberOfValues));
	}

	private Row createRow(int numberOfValues) {
		StringBuffer valueString = new StringBuffer();
		valueString.append("(");
		for (int i = 0; i < numberOfValues; i++) {
			valueString.append(TEST_VALUE1);
			valueString.append(",");
		}
		valueString.delete(valueString.length() - 1, valueString.length());

		Row row = new Row(valueString.toString());
		assertThat(row.getValues()).hasSize(numberOfValues);

		return row;
	}
}
