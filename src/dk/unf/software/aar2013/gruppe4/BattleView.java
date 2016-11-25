package dk.unf.software.aar2013.gruppe4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.view.View;

public class BattleView extends View {

	float width;
	float height;
	float rowsX;
	float columnsY;
	Paint paintLine;

	Paint sea;
	Paint ship;
	Paint miss;
	Paint hit;
	Grid grid = new Grid(null);
	int shotx = 1;
	int shoty = 1;

	public BattleView(Context context) {
		this(context, null);
	}

	public BattleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paintLine = new Paint();
		sea = new Paint();
		ship = new Paint();
		miss = new Paint();
		hit = new Paint();
	}

	public BattleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	@Override
	public void onDraw(Canvas canvas) {

		height = (float) (canvas.getWidth()/12*10.5);
		width = (float) (canvas.getWidth()/12*10.5);
		rowsX = 11;
		columnsY = 11;
		sea.setColor(Color.BLUE);
		ship.setColor(Color.BLACK);
		miss.setColor(Color.LTGRAY);
		hit.setColor(Color.RED);

		// Her laver vi de vertikale streger i spillet

		for (int i = 0; i <= rowsX; i++) {
			canvas.drawLine((width / rowsX) * i, 0,
					(width / rowsX) * i, height, paintLine);
		}

		// Her laver vi de horisontale streger i spillet
		for (int i = 0; i <= columnsY; i++) {
			canvas.drawLine(0, (height / columnsY) * i, width,
					(height / columnsY) * i, paintLine);
		}
		//her tegner vi tallene langs siderne
		for (int i = 1; i < 11; i++){
		canvas.drawText(""+ i, (width / rowsX) * (i + 0.5f), (height / columnsY) * 0.5f, ship);
		}
		for (int i = 1; i < 11; i++){
			canvas.drawText(""+ i, (width / rowsX) * 0.5f, (height / columnsY) * (i + 0.5f), ship);
			}
		// tegner vandet / felterne...
		for (int paintx = 1; paintx < rowsX; paintx++) {
			for (int painty = 1; painty < columnsY; painty++) {
				canvas.drawRect((width / rowsX) * paintx + 1,
						(height / columnsY) * painty + 1, (width / rowsX)
								* (paintx + 1) - 1, (height / columnsY)
								* (painty + 1) - 1, sea);
			}
		}
		DrawHit(canvas);
	}

	public void DrawHit(Canvas canvas) {
		for (int shipx = 0; shipx < 10; shipx++) {
			for (int shipy = 0; shipy < 10; shipy++) {
				if (grid.shotAt(shipx, shipy)) {
					if (grid.shipAt(shipx, shipy)) {
						canvas.drawCircle((width / rowsX) * (shipx + 1.5f),
								(height / columnsY) * (shipy + 1.5f), width
										/ (rowsX) / 2, hit);
					} else {
						canvas.drawCircle((width / rowsX) * (shipx + 1.5f),
								(height / columnsY) * (shipy + 1.5f), width
										/ (rowsX) / 2, miss);
					}
				}

			}
		}
	}

	public void DrawShip(Canvas canvas) {
		for (int shipx = 0; shipx < 10; shipx++) {
			for (int shipy = 0; shipy < 10; shipy++) {
				if (grid.shipAt(shipx, shipy)) {
					canvas.drawCircle(((width / rowsX) * (shipx + 0.5f)),
							((height / columnsY) * (shipy + 0.5f)), width
									/ rowsX / 2, ship);
				}
			}
		}
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
		invalidate();
	}
}
