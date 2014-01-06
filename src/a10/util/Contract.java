package a10.util;

public class Contract {

	private Contract() {

	}

	public static void requires(boolean condition) {
		try {
			if (!condition)
				throw new ContractViolatedException(
						"Contract broken! It will cost ya!");
		} catch (ContractViolatedException e) {
			e.printStackTrace();
		}
	}

	public static void requires(boolean... conditions) {
		for (boolean condition : conditions) {
			requires(condition);
		}
	}

	public static class ContractViolatedException extends Exception {
		private static final long serialVersionUID = 1L;

		public ContractViolatedException(String message) {
			super(message);
		}
	}

}
