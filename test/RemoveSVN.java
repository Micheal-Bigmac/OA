	import java.io.File;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;

	public class RemoveSVN {
		@SuppressWarnings("static-access")
		public static void main(String[] args) throws Exception {
			new FolderDeleter().deleteDirs("E:\\myeclipseworkspace2\\OA");
		}
	}
	
	

	class FolderDeleter {

		private static java.io.File m_root;
		@SuppressWarnings("rawtypes")
		private static ArrayList m_dirs;

		/**
		 * 递归删除文件夹以及子文件夹
		 * 
		 * @param dir
		 *            文件夹绝对路径
		 * @throws Exception
		 *             删除文件异常
		 */
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void deleteDirs(java.io.File dir) throws Exception {
			m_root = dir;
			m_dirs = new ArrayList();
			if (!m_root.isDirectory()) {
				throw new Exception("Exception:" + m_root.toString()
						+ " is not a director");
			} else {
				// delete all director
				try {
					m_dirs.add(m_root);
					myDelete();
				} catch (Exception e) {
					throw e;
				}
			}
		}

		/**
		 * 
		 * @param dirPath
		 *            String a director file path;
		 * @throws Exception
		 *             if dirPath is not a director file path
		 */
		public static void deleteDirs(String dirPath) throws Exception {
			m_root = new File(dirPath);
			deleteDirs(m_root);
		}

		/**
		 * 递归删除文件夹以及子文件夹
		 * 
		 * @param dirPath
		 *            文件夹绝对路径
		 * @throws Exception
		 *             删除文件异常
		 */
		public static void deleteSubDirs(String dirPath) throws Exception {
			m_root = new java.io.File(dirPath);
			deleteSubDirs(m_root);
		}

		/**
		 * 递归删除文件夹以及子文件夹
		 * 
		 * @param dir
		 *            文件夹绝对路径
		 * @throws Exception
		 *             删除文件异常
		 */
		@SuppressWarnings("rawtypes")
		public static void deleteSubDirs(java.io.File dir) throws Exception {
			m_root = dir;
			m_dirs = new ArrayList();
			if (!m_root.isDirectory()) {
				throw new Exception("Exception:" + m_root.toString()
						+ " is not a director");
			} else {
				for (int i = 0; i < m_dirs.size(); i++) {
					System.out.println(((File) m_dirs.get(i)).toString());
				}
				try {
					myDelete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * visit all a director and save them in a list
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static void visitAll(java.io.File tempRoot) throws Exception {
			java.io.File[] dirs = tempRoot.listFiles();
			if (dirs != null) {
				List dirsList = Arrays.asList(dirs);
				if (dirsList == null) {
					try {
						if (!tempRoot.delete())
							throw new Exception("Exception: delete file "
									+ tempRoot.getName() + " false!");
					} catch (Exception e) {
						throw e;
					}
				} else {
					m_dirs.addAll(dirsList);
					for (int i = 0; i < dirsList.size(); i++) {
						tempRoot = (java.io.File) dirsList.get(i);
						visitAll(tempRoot);
					}
				}
			}
		}


		private static void myDelete() throws Exception {
			visitAll(m_root);
			if (m_dirs != null) {
				for (int i = m_dirs.size() - 1; i >= 0; i--) {
					java.io.File f = (java.io.File) m_dirs.remove(i);
					String fileName = f.toString();
					if (fileName.contains(".svn")) {
						System.out.print(">>Deleting:");
						System.err.print(fileName+"\n");
						f.delete();
					}
					if (fileName.contains(".idea")) {
						System.out.print(">>Deleting:");
						System.err.print(fileName+"\n");
						f.delete();
					}
					if (fileName.contains(".git")) {
						System.out.print(">>Deleting:");
						System.err.print(fileName+"\n");
						f.delete();
					}

				}
			}
		}
	}

