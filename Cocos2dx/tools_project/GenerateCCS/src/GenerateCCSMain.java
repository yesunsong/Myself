public class GenerateCCSMain {

	public static void main(String[] args) {
		String sourceDir = "";
		String outputDir = "";
		try {
			sourceDir = args[0];
			outputDir = args[1];
			System.out.println(sourceDir);
			System.out.println(outputDir);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("无效的参数/参数不足");
			return;
		}

		GenerateCCS generateCCS = new GenerateCCS(sourceDir,outputDir);
	}

}