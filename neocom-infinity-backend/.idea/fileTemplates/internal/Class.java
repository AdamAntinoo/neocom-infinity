#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class ${NAME} {
private ${NAME}(){}
	// - B U I L D E R
	public static class Builder {
		private ${NAME} onConstruction;

		public Builder() {
			this.onConstruction = new ${NAME}();
		}

		public ${NAME}.Builder with<Field>( final <Field> <Field> ) {
			this.onConstruction.<Field> = <Field>;
			return this;
		}
		public ${NAME} build() {
			return this.onConstruction;
		}
	}
}