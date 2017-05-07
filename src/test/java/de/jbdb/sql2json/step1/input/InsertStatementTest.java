package de.jbdb.sql2json.step1.input;

import static de.jbdb.sql2json.Sql2JSONTestObjects.TESTINSERT;
import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_COLUMN;
import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_TABLE;
import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_VALUE1;
import static de.jbdb.sql2json.Sql2JSONTestObjects.TEST_VALUE2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InsertStatementTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void constructorWithNullParameter_ThrowsIllegalArgumentException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(org.hamcrest.CoreMatchers.containsString("parameter was null"));

		new InsertStatement(null);
	}

	@Test
	public void constructorWithEmptyParameter_ThrowsIllegalArgumentException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(org.hamcrest.CoreMatchers.containsString("parameter was empty"));

		new InsertStatement("");
	}

	@Test
	public void constructorWithSimpleInsert() throws Exception {

		InsertStatement insertStatement = new InsertStatement(String.join(" ", TESTINSERT));

		assertThat(insertStatement.getTableName()).isEqualTo(new TableName(TEST_TABLE));
		assertThat(insertStatement.getColumnNames()).isNotNull();
		assertThat(insertStatement.getColumnNames()).hasSize(1);
		assertThat(insertStatement.getColumnNames().get(0)).isEqualTo(new ColumnName(TEST_COLUMN));

		List<Row> rows = insertStatement.getValueRows();
		assertThat(rows).describedAs("Rows").isNotNull();
		assertThat(rows).describedAs("Rows").isNotEmpty();
		assertThat(rows).hasSize(1);

		List<Value> values = rows.get(0).getValues();
		assertThat(values).describedAs("Values").isNotNull();
		assertThat(values).describedAs("Values").isNotEmpty();
		assertThat(values).hasSize(2);
		assertThat(values).contains(new Value(TEST_VALUE1), new Value(TEST_VALUE2));
	}

	// TODO: more complex test case
	// TODO: test insert with lower case letters
}
