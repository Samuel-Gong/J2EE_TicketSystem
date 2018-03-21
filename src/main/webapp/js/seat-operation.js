/**
 * 使用二维数组，保存座位图，默认大小为4*10
 */
let seatMap = [
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'],
    ['a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a']
];

/**
 * 根据行数和列数初始化座位图的大小
 * @param rowNum    行数
 * @param columnNum 列数
 * @param seats     座位图的json数组
 *
 "seatMap":[
 {
   "venueSeatId":{
     "row":10,
     "column":10
   },
   "hasSeat":true
 }
 ]
 */

/**
 * 初始化seatMap为 rowNum * columnNum 大小的数组
 * @param rowNum    座位行数
 * @param columnNum 座位列数
 */
function initSeatMap(rowNum, columnNum) {
    seatMap = new Array(rowNum);
    $.each(seatMap, function (index, val) {
        seatMap[index] = new Array(columnNum);
        for (let i = 0; i < columnNum; i++) {
            seatMap[index][i] = "";
        }
    });
}

function fillSeatMapWithDefaultType(rowNum, columnNum, seats) {
    initSeatMap(rowNum, columnNum);
    $.each(seats, function (index, seat) {
        let seat_row = seat.row;
        let seat_column = seat.column;
        seatMap[seat_row - 1][seat_column - 1] = seat.hasSeat ? 'a' : '_';
    });
}

function fillSeatMapWithType(rowNum, columnNum, seatsWithType) {
    initSeatMap(rowNum, columnNum);
    $.each(seatsWithType, function (index, seat) {
        let seat_row = seat.row;
        let seat_column = seat.column;
        seatMap[seat_row - 1][seat_column - 1] = seat.typeChar;
    });
}

/**
 * 保存座位信息
 */
let seatInfo = {
    a: {
        //Custom CSS classes which should be applied to seats.
        //'seat-red seat-big' or ['seat-red', 'seat-big']
        //点击座位，转换为空位
        // click: seat2space,
    },

    /**
     * 添加一个表示座位的字符
     * @param typeChar  表示座位类型的字符
     * @param obj       该座位类型的对象
     */
    addTypeChar(typeChar, obj) {
        this[typeChar] = obj;
    },
    /**
     * 删除一个表示座位类型的字符
     * @param typeChar  表示座位类型的字符
     */
    removeTypeChar(typeChar) {
        delete this[typeChar];
    },
    /**
     * 为座位信息中的一个表示字符添加点击事件
     * @param typeChar  特定字符
     * @param func      点击事件处理方法
     */
    addClick(typeChar, func) {
        this[typeChar].click = func;
    },
    /**
     * 删除某个表示字符的点击方法
     * @param typeChar  表示座位类型的字符
     */
    removeClick(typeChar) {
        delete this[typeChar].click;
    }
};

/**
 * 座位图例
 */
let legendInfo = {
    //jQuery reference to a DIV element where legend should be rendered.
    node: $('#legend-box'),
    //An array of legend item details. Each array element should be a three-element array: [ character, status, description ]
    items: [
        ['a', 'available', '有座位']
    ],

    /**
     * 向items中添加一个item
     *
     * @param item 需要添加的item
     */
    addItem(item) {
        this.items.push(item);
    },
    /**
     * 根据表示item的字符删除该item
     * @param typeChar  表示该item的字符
     */
    removeItem(typeChar) {
        let index = this.indexOfItem(typeChar);
        console.log(index);
        this.items.splice(index, 1);
    },
    /**
     * 根据表示item的字符查找在items中的位置
     * @param typeChar
     */
    indexOfItem(typeChar) {
        let result;
        $.each(this.items, function (index, item) {
            if (item[0] === typeChar) {
                result = index;
                return false;
            }
        });
        return result;
    }

};

/**
 * 添加一行
 */
function add_row() {
    if (seatMap.length === 15) {
        alert("座位行数不可以大于15");
    }
    else {
        //新建一行，长度与当前座位图中每一行的长度相等
        let rowLength = seatMap[0].length;
        let newRow = [];
        for (let i = 0; i < rowLength; i++) {
            newRow.push('a');
        }
        seatMap.push(newRow);
        rerenderSeats();
    }
}

/**
 * 增加一列
 */
function add_column() {
    if (seatMap[0].length === 20) {
        alert("座位列数不可以大于20");
    }
    else {
        $.each(seatMap, function (index, val) {
            seatMap[index].push("a");
        });
        rerenderSeats();
    }
}

/**
 * 删除一行
 */
function delete_row() {
    if (seatMap.length === 3) {
        alert("座位行数不可以小于3");
    }
    else {
        seatMap.pop();
    }
    rerenderSeats();
}

/**
 * 删除一列
 */
function delete_column() {
    if (seatMap[0].length === 10) {
        alert("座位列数不可以小于10");
    }
    else {
        $.each(seatMap, function (index, val) {
            seatMap[index].pop();
        });
        rerenderSeats();
    }
}

/**
 * 将座位设置为空
 */
function seat2space() {
    //例如第一排第二列的id为: 1_2
    let thisId = this.node().attr("id");
    let rowAndColumn = thisId.split("_");
    let row = rowAndColumn[0];
    let column = rowAndColumn[1];

    seatMap[row - 1][column - 1] = '_';
    rerenderSeats();
}

/**
 * 将空位设置为座位
 */
function space2seat() {
    let row = $(this).parent().prevAll().length + 1;
    let column = $(this).prevAll().length;

    seatMap[row - 1][column - 1] = 'a';

    rerenderSeats();
}

/**
 * 将seatMap的行转化为string
 */
function seatMapRow2String() {

    let formatSeatMap = [];

    $.each(seatMap, function (index, val) {
        formatSeatMap.push(val.join(""));
    });

    return formatSeatMap;
}

/**
 * 渲染座位
 */
function renderSeats() {
    $("#seat-map").seatCharts({
        //必须的
        //每个字符表示一个座位，下划线表示没有座位
        //每一行的列数必须相等
        map: seatMapRow2String(),
        seats: seatInfo,
        naming: {
            top: false,
            //默认getId函数
            getId: function (character, row, column) {
                return row + '_' + column;
            },
            //默认getLabel函数
            //Labels will be displayed over seats
            //so if you don't want any labels, just return an empty string.
            getLabel: function (character, row, column) {
                return character;
            }
        },
        //legend,  默认显示该信息
        legend: legendInfo
        // click: function () {
        //     if (this.status() === 'available') {
        //         return 'available';
        //     } else {
        //         if (this.char() === '_') {
        //             alert(this.status() + "\n" + this.node() + "\n" + this.data() + "\n" + this.char());
        //         }
        //         // return this.style();
        //     }
        // }
        ,
        /**
         * 是否显示legend的信息
         * @param isShowLegend  true or false
         */
        showLegend(isShowLegend) {
            if (!isShowLegend) {
                delete this.legend;
            }
        }
    });
}

/**
 * 重新渲染座位
 */
function rerenderSeats() {
    //删除原来的seat-map div
    $("#seat-map").remove();
    //删除座位类型的list
    $(".seatCharts-legendList").remove();
    //添加新的seat-map元素
    let newSeatMap = "<div id='seat-map'></div>";
    //将新的seat-map元素添加到外层container中
    $("#seat-map-container").append(newSeatMap);
    //重新渲染座位图
    renderSeats();
}