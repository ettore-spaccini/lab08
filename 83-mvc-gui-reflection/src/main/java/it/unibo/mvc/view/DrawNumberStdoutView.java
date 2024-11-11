package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStdoutView implements DrawNumberView{
    @Override
    public void setController(DrawNumberController observer) {
        /*
         * Only output
         */
    }

    @Override
    public void start() {
        /*
         * it does not need to be started
         */
    }

    @Override
    public void result(final DrawResult res) {
       System.out.println(res.getDescription());
    }

}
