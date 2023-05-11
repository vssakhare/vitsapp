/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //document.getElementById('chartdiv1').style.visibility = 'visible';
    document.getElementById('chartdiv').style.visibility = 'visible';
    //config_cons.data.datasets.splice(0, 1);
    // config_cons.data.labels.splice(0,1);
    //var ctx_cons = document.getElementById('test_chart_area').getContext('2d');
    var cardchart1 = document.getElementById('card-chart1').getContext('2d');
    var cardchart2 = document.getElementById('card-chart2').getContext('2d');
    var cardchart3 = document.getElementById('card-chart3').getContext('2d');
    var cardchart4 = document.getElementById('card-chart4').getContext('2d');
    var vSubmitmax = document.getElementById('vSubmitmax').value;
    if (vSubmitmax === 0)
    {
        vstepSize = vSubmitmax;
    } else {
        var vstepSize = vSubmitmax / 5;
    }


    var intstepValue = Math.round(vstepSize);
    var k = $('#j').val();
    var l = $('#i').val();
    var pTotal = [];
    var zone = [];
    var paid = [];
    var pCash = [];
    var pTech = [];
    var pAcc = [];
    var pMoreTotal = [];
    var pLessTotal = [];
    var vSubmitFeeType = [];
    var feeType = [];
    var zoneFeeType = [];

    var vSubmit = [];
    for (i = 1; i <= k; i++) {
        if ($('#pTotal' + i).val() !== undefined
                && $('#zone' + i).val() !== undefined
                && $('#pCash_more' + i).val() !== undefined
                && $('#pTech_more' + i).val() !== undefined
                && $('#pAcc_more' + i).val() !== undefined
                && $('#pCash_less' + i).val() !== undefined
                && $('#pTech_less' + i).val() !== undefined
                && $('#pAcc_less' + i).val() !== undefined
                && $('#vSubmit' + i).val() !== undefined
                )
        {
            pTotal.push($('#pTotal' + i).val());
            zone.push($('#zone' + i).val());
            paid.push(parseFloat($('#paid' + i).val()));
            pCash.push(parseFloat($('#pCash_more' + i).val()) + parseFloat($('#pCash_less' + i).val()));
            pTech.push(parseFloat($('#pTech_more' + i).val()) + parseFloat($('#pTech_less' + i).val()));
            pAcc.push(parseFloat($('#pAcc_more' + i).val()) + parseFloat($('#pAcc_less' + i).val()));
            vSubmit.push($('#vSubmit' + i).val());
            pMoreTotal.push(parseFloat($('#pCash_more' + i).val()) + parseFloat($('#pTech_more' + i).val()) + parseFloat($('#pAcc_more' + i).val()));
            pLessTotal.push(parseFloat($('#pCash_less' + i).val()) + parseFloat($('#pTech_less' + i).val()) + parseFloat($('#pAcc_less' + i).val()));

        }

    }
 /*    for (i = 1; i <= l; i++) {
       
        {
            zoneFeeType.push($('#zoneFeeType' + i).val());
            vSubmitFeeType.push($('#vSubmitFeeType' + i).val());
             feeType.push($('#feeType' + i).val());
            
            
        }

    }
    
console.log(vSubmitFeeType);
console.log(feeType);
console.log(zoneFeeType);


var inchartdatasets = [];
		// loop through the in chart lables and in chart values to create the dataset variable dynamicaly
		for (var i = 0; i < l; i++) {
			var data = {
				label: feeType[i],
        		data: [vSubmitFeeType[i]]
			};
			inchartdatasets.push(data);
		}
                
    function getUnique(array){
        var uniqueArray = [];
        
        // Loop through array values
        for(i=0; i < array.length; i++){
            if(uniqueArray.indexOf(array[i]) === -1) {
                uniqueArray.push(array[i]);
            }
        }
        return uniqueArray;
    }
    
  
    var uniqueNames = getUnique(zoneFeeType);
    console.log(uniqueNames.length);
	for (var i = 0; i <= uniqueNames.length; i++) {
            
            
        }*/



    var newDataset_cons1 = {

        labels: zone,
        fill: true,
        datasets: [
            {
                //backgroundColor: '#00000066',
                data: pCash,

                options: {
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    suggestedMin: 0,
                                    max: vSubmitmax
                                }
                            }]

                    }
                }

            }]};
    var newDataset_cons2 = {

        labels: zone,
        fill: true,
        datasets: [
            {
                //backgroundColor: '#00000066',
                data: pAcc,

                options: {
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    suggestedMin: 0,
                                    max: vSubmitmax
                                }
                            }]

                    }
                }
            }]};
    var newDataset_cons3 = {

        labels: zone,
        fill: true,
        datasets: [
            {//backgroundColor: '#00000069',
                data: pTech,

                options: {

                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    suggestedMin: 0,
                                    precision: 0,
                                    max: vSubmitmax,
                                    callback: function (value) {
                                        if (value % 1 === 0) {
                                            return value;
                                        }
                                    }

                                }
                            }]

                    }
                }
            }]};
    
  
    
    var newDataset_cons4 = {

        labels: zone,
        fill: true,
        datasets: [
            {
                //backgroundColor: '#00000066',
                data: vSubmit,

                options: {
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    suggestedMin: 0,
                                    max: vSubmitmax
                                }
                            }]

                    }
                }
            }]
               };

    graphDatum = {
        pLess: {
            dataPoints: [

            ]},
        pMore: {

            dataPoints: [

            ]
        }
    };
    for (var i = 0; i < zone.length; i++) {


        graphDatum.pLess.dataPoints.push({label: $.trim(zone[i]), y: pLessTotal[i]})

    }
    //console.log(graphDatum.pLess.dataPoints);
    for (var j = 0; j < zone.length; j++) {


        graphDatum.pMore.dataPoints.push({label: $.trim(zone[j]), y: pMoreTotal[j]})

    }
    window.myPie_cons = new CanvasJS.Chart("columnChart", {
        animationEnabled: true,
        backgroundColor: "#eee",
        dataPointWidth: 35,

        title: {
            //fontSize: 18,ontWeight:"lighter",text: "Zone Wise Total Pending Invoices"
        }, axisY: {
            precision: 0,
            scaleIntegersOnly: true,
            beginAtZero: true,

            labelFormatter: function (e) {
                if (Number.isInteger(e.value))
                    return  e.value;
                else
                    return "";
            },
            callback: function (value) {
                if (value % 1 === 0) {
                    alert("in Return");
                    return value;
                }
            },
            ticks: {

                beginAtZero: true,
                precision: 0,
                callback: function (value) {
                    if (value % 1 === 0) {
                        return value;
                    }
                },
            }
        },
        axisY2: {
            callback: function (value) {
                if (value % 1 === 0) {
                    return value;
                }
            }, precision: 0,
            ticks: {
                beginAtZero: true,
                precision: 0,
                callback: function (value) {
                    if (value % 1 === 0) {
                        return value;
                    }
                }
            }
        },
        toolTip: {
            shared: true
        },
        legend: {
            cursor: "pointer",
            itemclick: toggleDataSeries

        },
        data: [{type: "column",
                color: 'rgba(54, 162, 235, 1)',
                name: "Total Pending Invoices less than 30 days",
                legendText: "Total Pending Invoices less than 30 days",
                showInLegend: true,
                dataPoints: graphDatum.pLess.dataPoints
            },
            {type: "column",
                name: "Total Pending Invoices more than 30 days",
                legendText: "Total Pending Invoices more than 30 days",

                showInLegend: true,
                dataPoints: graphDatum.pMore.dataPoints
            }
        ]});
    myPie_cons.canvas.setAttribute("id", "test_chart_area");

    $(document).ready(function () {
        $(window).resize(function () {
            myPie_cons.options.width = $('#chartdiv').width();
            myPie_cons.options.height = $('#chartdiv').height();
            myPie_cons.render();
        });
    });

    window.myPie_cons.render();
    function toggleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        window.myPie_cons.render();
    }
    window.myPie_cons1 = new Chart(cardchart1, {//pending for payment
        type: "bar",
        animationEnabled: true,
        data: newDataset_cons1,
        options: {
            legend: {
                display: false
            },
            scales: {
                xAxes: [{maxBarThickness: 15,
                        gridLines: {
                            drawOnChartArea: false,
                            dislay: false,
                            lineWidth: 1
                        },
                        ticks: {
                            display: false //this will remove only the label
                        }
                    }],
                yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            precision: 0,
                            max: vSubmitmax, stepSize: intstepValue,
                            callback: function (value) {
                                if (value % 1 === 0) {
                                    return value;
                                }
                            }

                        },
                        gridLines: {
                            drawOnChartArea: false,
                            lineWidth: 1
                        }
                    }]
            }
        }


    });
    window.myPie_cons2 = new Chart(cardchart2, {//pending at accounts
        type: "bar",
        animationEnabled: true,
        data: newDataset_cons2,
        options: {legend: {
                display: false
            },
            scales: {
                xAxes: [{maxBarThickness: 15, ticks: {
                            display: false //this will remove only the label
                        },
                        gridLines: {
                            drawOnChartArea: false
                        }
                    }],
                yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            drawOnChartArea: false,
                            max: vSubmitmax,

                            stepSize: intstepValue,
                            callback: function (value) {
                                if (value % 1 === 0) {
                                    return value;
                                }
                            }
                        },
                        gridLines: {
                            drawOnChartArea: false,
                            lineWidth: 1
                        }
                    }]
            }
        }


    });
    window.myPie_cons3 = new Chart(cardchart3, {//pending at technical
        type: "bar",
        animationEnabled: true,
        data: newDataset_cons3,

        options: {legend: {
                display: false
            },
            scales: {
                xAxes: [{maxBarThickness: 15, ticks: {
                            display: false //this will remove only the label
                        },
                        gridLines: {
                            drawOnChartArea: false
                        }
                    }],
                yAxes: [{gridlines: {count: -1},
                        ticks: {

                            beginAtZero: true,
                            precision: 0,
                            max: vSubmitmax, stepSize: intstepValue,
                            callback: function (value) {
                                if (value % 1 === 0) {
                                    return value;
                                }
                            }
                        },
                        gridLines: {
                            drawOnChartArea: false,
                            lineWidth: 1
                        }
                    }]
            }
        }
    });
    window.myPie_cons4 = new Chart(cardchart4, {//submitted by vendor
        type: "bar",
        animationEnabled: true,
        data: newDataset_cons4,
        options: {legend: {
                display: false
            },
            scales: {
                xAxes: [{maxBarThickness: 15,
                        
                       
                        ticks: {
                            display: false //this will remove only the label
                        },
                        gridLines: {
                            drawOnChartArea: false
                        }
                    }],
                yAxes: [{gridlines: {count: -1},
                        ticks: {precision: 0,
                            beginAtZero: true, max: vSubmitmax, stepSize: intstepValue,
                            callback: function (value) {
                                if (value % 1 === 0) {
                                    return value;
                                }
                            }
                        },
                        gridLines: {
                            drawOnChartArea: false,
                            lineWidth: 1
                        }
                    }]
            }
      /*      onClick:function(e){
        var activePoints = window.myPie_cons4.getElementsAtEvent(e);
      if (activePoints[0]) {
         var chartData = activePoints[0]['_chart'].config.data;
         var idx = activePoints[0]['_index'];

         var label = chartData.labels[idx];
         var value = chartData.datasets[0].data[idx];
       //  var color = chartData.datasets[0].backgroundColor[idx]; //Or any other data you wish to take from the clicked slice

         alert(label + ' ' + value ); //Or any other function you want to execute. I sent the data to the server, and used the response i got from the server to create a new chart in a Bootstrap modal.
       }
      }*/
        }
    });
    
/*function addData(chart, label, color, data) {
		chart.data.datasets.push({
	    label: label,
      backgroundColor: color,
      data: data
    });
    chart.update();
}

// inserting the new dataset after 3 seconds
setTimeout(function() {
	addData(window.myPie_cons4, '# of Votes 2017', '#ff0000', vSubmit);
}, 3000);*/

});


function hide() {
    var u = window.location.href;
    if (u.indexOf("legaldashboard") === -1) {
        var uiActionName = "legaldashboard";
        var action = "home";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    }

}


