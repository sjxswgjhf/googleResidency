public class minarea302 {


    public int minArea(char[][] image, int x, int y) {

        int top = x, bot = x;
        int left = y, right = y;
        for(int i = 0; i < image.length; i++){
            for(int j = 0; j < image[0].length; j++){
                if(image[i][j] == '1'){
                    top = Math.min(top, i);
                    bot = Math.max(bot, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }
        return (bot - top + 1) * (right - left + 1);
    }
}
