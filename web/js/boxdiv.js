/*

FREESTYLE MENUS v1.0 RC (c) 2001-2006 Angus Turnbull, http://www.twinhelix.com
Altering this notice or redistributing this file is prohibited.

*/

var isDOM=document.getElementById?1:0,isIE=document.all?1:0,isNS4=navigator.appName=='Netscape'&&!isDOM?1:0,isOp=self.opera?1:0,isDyn=isDOM||isIE||isNS4;
function getRef(i,p){p=!p?document:p.navigator?p.document:p;return isIE?p.all[i]:isDOM?(p.getElementById?p:p.ownerDocument).getElementById(i):isNS4?p.layers[i]:null};
function getSty(i,p){var r=getRef(i,p);return r?isNS4?r:r.style:null};if(!self.LayerObj)var LayerObj=new Function('i','p','this.ref=getRef(i,p);this.sty=getSty(i,p);return this');
function getLyr(i,p){return new LayerObj(i,p)};
function LyrFn(n,f){LayerObj.prototype[n]=new Function('var a=arguments,p=a[0],px=isNS4||isOp?0:"px";with(this){'+f+'}')};LyrFn('x','if(!isNaN(p))sty.left=p+px;else return parseInt(sty.left)');LyrFn('y','if(!isNaN(p))sty.top=p+px;else return parseInt(sty.top)');if(typeof addEvent!='function'){var addEvent=function(o,t,f,l){var d='addEventListener',n='on'+t,rO=o,rT=t,rF=f,rL=l;if(o[d]&&!l)return o[d](t,f,false);if(!o._evts)o._evts={};if(!o._evts[t]){o._evts[t]=o[n]?{b:o[n]}:{};o[n]=new Function('e','var r=true,o=this,a=o._evts["'+t+'"],i;for(i in a){o._f=a[i];r=o._f(e||window.event)!=false&&r;o._f=null}return r');if(t!='unload')addEvent(window,'unload',function(){removeEvent(rO,rT,rF,rL)})}if(!f._i)f._i=addEvent._i++;o._evts[t][f._i]=f};addEvent._i=1;var removeEvent=function(o,t,f,l){var d='removeEventListener';if(o[d]&&!l)return o[d](t,f,false);if(o._evts&&o._evts[t]&&f._i)delete o._evts[t][f._i]}}function FSMenu2(myName,nested,cssProp,cssVis,cssHid){this.myName=myName;this.nested=nested;this.cssProp=cssProp;this.cssVis=cssVis;this.cssHid=cssHid;this.cssLitClass='highlighted';this.menus={root:new FSMenu2Node('root',true,this)};this.menuToShow=[];this.mtsTimer=null;this.showDelay=0;this.switchDelay=125;this.hideDelay=50;this.showOnClick=0;this.hideOnClick=true;this.animInSpeed=0.2;this.animOutSpeed=0.2;this.animations=[]};FSMenu2.prototype.show=function(mN){with(this){menuToShow.length=arguments.length;for(var i=0;i<arguments.length;i++)menuToShow[i]=arguments[i];clearTimeout(mtsTimer);if(!nested)mtsTimer=setTimeout(myName+'.menus.root.over()',10)}};FSMenu2.prototype.hide=function(mN){with(this){clearTimeout(mtsTimer);if(menus[mN])menus[mN].out()}};FSMenu2.prototype.hideAll=function(){with(this){for(var m in menus)if(menus[m].visible&&!menus[m].isRoot)menus[m].hide(true)}};
function FSMenu2Node(id,isRoot,obj){this.id=id;this.isRoot=isRoot;this.obj=obj;this.lyr=this.child=this.par=this.timer=this.visible=null;this.args=[];var node=this;this.over=function(evt){with(node)with(obj){if(isNS4&&evt&&lyr.ref)lyr.ref.routeEvent(evt);clearTimeout(timer);clearTimeout(mtsTimer);if(!isRoot&&!visible)node.show();if(menuToShow.length){var a=menuToShow,m=a[0];if(!menus[m]||!menus[m].lyr.ref)menus[m]=new FSMenu2Node(m,false,obj);var c=menus[m];if(c==node){menuToShow.length=0;return}clearTimeout(c.timer);if(c!=child&&c.lyr.ref){c.args.length=a.length;for(var i=0;i<a.length;i++)c.args[i]=a[i];var delay=child?switchDelay:showDelay;c.timer=setTimeout('with('+myName+'){menus["'+c.id+'"].par=menus["'+node.id+'"];menus["'+c.id+'"].show()}',delay?delay:1)}menuToShow.length=0}if(!nested&&par)par.over()}};this.out=function(evt){with(node)with(obj){if(isNS4&&evt&&lyr&&lyr.ref)lyr.ref.routeEvent(evt);clearTimeout(timer);if(!isRoot&&hideDelay>=0){timer=setTimeout(myName+'.menus["'+id+'"].hide()',hideDelay);if(!nested&&par)par.out()}}};if(this.id!='root')with(this)with(lyr=getLyr(id))if(ref){if(isNS4)ref.captureEvents(Event.MOUSEOVER|Event.MOUSEOUT);addEvent(ref,'mouseover',this.over);if(obj.nested){addEvent(ref,'focus',this.over);addEvent(ref,'click',this.over);addEvent(ref,'blur',this.out)}}};FSMenu2Node.prototype.show=function(forced){with(this)with(obj){if(!lyr||!lyr.ref)return;if(par){if(par.child&&par.child!=this)par.child.hide();par.child=this}var offR=args[1],offX=args[2],offY=args[3],lX=0,lY=0,doX=''+offX!='undefined',doY=''+offY!='undefined';if(self.page&&offR&&(doX||doY)){with(page.elmPos(offR,par.lyr?par.lyr.ref:0))lX=x,lY=y;if(doX)lyr.x(lX+eval(offX));if(doY)lyr.y(lY+eval(offY))}if(offR)lightParent(offR,1);visible=1;if(obj.onshow)obj.onshow(id);lyr.ref.parentNode.style.zIndex='2';setVis(1,forced)}};FSMenu2Node.prototype.hide=function(forced){with(this)with(obj){if(!lyr||!lyr.ref||!visible)return;if(isNS4&&self.isMouseIn&&isMouseIn(lyr.ref))return show();if(args[1])lightParent(args[1],0);if(child)child.hide();if(par&&par.child==this)par.child=null;if(lyr){visible=0;if(obj.onhide)obj.onhide(id);lyr.ref.parentNode.style.zIndex='1';setVis(0,forced)}}};FSMenu2Node.prototype.lightParent=function(elm,lit){with(this)with(obj){if(!cssLitClass||isNS4)return;if(lit)elm.className+=(elm.className?' ':'')+cssLitClass;else elm.className=elm.className.replace(new RegExp('(\\s*'+cssLitClass+')+$'),'')}};FSMenu2Node.prototype.setVis=function(sh,forced){with(this)with(obj){if(lyr.forced&&!forced)return;lyr.forced=forced;lyr.timer=lyr.timer||0;lyr.counter=lyr.counter||0;with(lyr){clearTimeout(timer);if(sh&&!counter)sty[cssProp]=cssVis;var speed=sh?animInSpeed:animOutSpeed;if(isDOM&&speed<1)for(var a=0;a<animations.length;a++)animations[a](ref,counter,sh);counter+=speed*(sh?1:-1);if(counter>1){counter=1;lyr.forced=false}else if(counter<0){counter=0;sty[cssProp]=cssHid;lyr.forced=false}else if(isDOM){timer=setTimeout(myName+'.menus["'+id+'"].setVis('+sh+','+forced+')',50)}}}};FSMenu2.animSwipeDown=function(ref,counter,show){if(show&&(counter==0)){ref._fsm_styT=ref.style.top;ref._fsm_styMT=ref.style.marginTop;ref._fsm_offT=ref.offsetTop||0}var cP=Math.pow(Math.sin(Math.PI*counter/2),0.75);var clipY=ref.offsetHeight*(1-cP);ref.style.clip=(counter==1?((window.opera||navigator.userAgent.indexOf('KHTML')>-1)?'':'rect(auto,auto,auto,auto)'):'rect('+clipY+'px,'+ref.offsetWidth+'px,'+ref.offsetHeight+'px,0)');if(counter==1||(counter<0.01&&!show)){ref.style.top=ref._fsm_styT;ref.style.marginTop=ref._fsm_styMT}else{ref.style.top=((0-clipY)+(ref._fsm_offT))+'px';ref.style.marginTop='0'}};FSMenu2.animFade=function(ref,counter,show){var done=(counter==1);if(ref.filters){var alpha=!done?' alpha(opacity='+parseInt(counter*100)+')':'';if(ref.style.filter.indexOf("alpha")==-1)ref.style.filter+=alpha;else ref.style.filter=ref.style.filter.replace(/\s*alpha\([^\)]*\)/i,alpha)}else ref.style.opacity=ref.style.MozOpacity=counter/1.001};FSMenu2.animClipDown=function(ref,counter,show){var cP=Math.pow(Math.sin(Math.PI*counter/2),0.75);ref.style.clip=(counter==1?((window.opera||navigator.userAgent.indexOf('KHTML')>-1)?'':'rect(auto,auto,auto,auto)'):'rect(0,'+ref.offsetWidth+'px,'+(ref.offsetHeight*cP)+'px,0)')};FSMenu2.prototype.activateMenu=function(id,subInd){with(this){if(!isDOM||!document.documentElement)return;var fsmFB=getRef('FSMenu2-fallback');if(fsmFB){fsmFB.rel='alternate stylesheet';fsmFB.disabled=true}var a,ul,li,parUL,mRoot=getRef(id),nodes,count=1;var lists=mRoot.getElementsByTagName('ul');for(var i=0;i<lists.length;i++){li=ul=lists[i];while(li){if(li.nodeName.toLowerCase()=='li')break;li=li.parentNode}if(!li)continue;parUL=li;while(parUL){if(parUL.nodeName.toLowerCase()=='ul')break;parUL=parUL.parentNode}a=null;for(var j=0;j<li.childNodes.length;j++)if(li.childNodes[j].nodeName.toLowerCase()=='a')a=li.childNodes[j];if(!a)continue;var menuID=myName+'-id-'+count++;if(ul.id)menuID=ul.id;else ul.setAttribute('id',menuID);var sOC=(showOnClick==1&&li.parentNode==mRoot)||(showOnClick==2);var evtProp=navigator.userAgent.indexOf('Safari')>-1||isOp?'safRtnVal':'returnValue';var eShow=new Function('with('+myName+'){var m=menus["'+menuID+'"],pM=menus["'+parUL.id+'"];'+(sOC?'if((pM&&pM.child)||(m&&m.visible))':'')+' show("'+menuID+'",this)}');var eHide=new Function('e','if(e.'+evtProp+'!=false)'+myName+'.hide("'+menuID+'")');addEvent(a,'mouseover',eShow);addEvent(a,'focus',eShow);addEvent(a,'blur',eHide);if(sOC)addEvent(a,'click',new Function('e',myName+'.show("'+menuID+'",this);if(e.cancelable&&e.preventDefault)e.preventDefault();e.'+evtProp+'=false;return false'));if(subInd)a.insertBefore(subInd.cloneNode(true),a.firstChild)}if(isIE&&!isOp){var aNodes=mRoot.getElementsByTagName('a');for(var i=0;i<aNodes.length;i++){addEvent(aNodes[i],'focus',new Function('e','var node=this.parentNode;while(node){if(node.onfocus)node.onfocus(e);node=node.parentNode}'));addEvent(aNodes[i],'blur',new Function('e','var node=this.parentNode;while(node){if(node.onblur)node.onblur(e);node=node.parentNode}'))}}if(hideOnClick)addEvent(mRoot,'click',new Function(myName+'.hideAll()'));menus[id]=new FSMenu2Node(id,true,this)}};var page={win:self,minW:0,minH:0,MS:isIE&&!isOp,db:document.compatMode&&document.compatMode.indexOf('CSS')>-1?'documentElement':'body'};page.elmPos=function(e,p){var x=0,y=0,w=p?p:this.win;e=e?(e.substr?(isNS4?w.document.anchors[e]:getRef(e,w)):e):p;if(isNS4){if(e&&(e!=p)){x=e.x;y=e.y};if(p){x+=p.pageX;y+=p.pageY}}if(e&&this.MS&&navigator.platform.indexOf('Mac')>-1&&e.tagName=='A'){e.onfocus=new Function('with(event){self.tmpX=clientX-offsetX;self.tmpY=clientY-offsetY}');e.focus();x=tmpX;y=tmpY;e.blur()}else while(e){x+=e.offsetLeft;y+=e.offsetTop;e=e.offsetParent}return{x:x,y:y}};if(isNS4){var fsmMouseX,fsmMouseY,fsmOR=self.onresize,nsWinW=innerWidth,nsWinH=innerHeight;document.fsmMM=document.onmousemove;self.onresize=function(){if(fsmOR)fsmOR();if(nsWinW!=innerWidth||nsWinH!=innerHeight)location.reload()};document.captureEvents(Event.MOUSEMOVE);document.onmousemove=function(e){fsmMouseX=e.pageX;fsmMouseY=e.pageY;return document.fsmMM?document.fsmMM(e):document.routeEvent(e)};
function isMouseIn(sty){with(sty)return((fsmMouseX>left)&&(fsmMouseX<left+clip.width)&&(fsmMouseY>top)&&(fsmMouseY<top+clip.height))}}

// With this divMenu2 object, 'false' means the menu elements are not nested.
var divMenu2 = new FSMenu2('divMenu2', false, 'visibility', 'visible', 'hidden');
divMenu2.cssLitClass = 'highlighted';
divMenu2.animInSpeed = 1;
divMenu2.animOutSpeed = 1;

FSMenu2.prototype.ieSelBoxFixShow = function(mN) { with (this)
{
 var m = menus[mN];
 if (!isIE || !window.createPopup) return;
 if (navigator.userAgent.match(/MSIE ([\d\.]+)/) && parseFloat(RegExp.$1) > 6.5)
  return;
 // Create a new transparent IFRAME if needed, and insert under the menu.
 if (!m.ifr)
 {
  m.ifr = document.createElement('iframe');
  m.ifr.src = 'about:blank';
  with (m.ifr.style)
  {
   position = 'absolute';
   border = 'none';
   filter = 'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';
  }
  m.lyr.ref.parentNode.insertBefore(m.ifr, m.lyr.ref);
 }
 // Position and show it on each call.
 with (m.ifr.style)
 {
  left = m.lyr.ref.offsetLeft + 'px';
  top = m.lyr.ref.offsetTop + 'px';
  width = m.lyr.ref.offsetWidth + 'px';
  height = m.lyr.ref.offsetHeight + 'px';
  visibility = 'visible';
 }
}};
FSMenu2.prototype.ieSelBoxFixHide = function(mN) { with (this)
{
 if (!isIE || !window.createPopup) return;
 var m = menus[mN];
 if (m.ifr) m.ifr.style.visibility = 'hidden';
}};
addEvent(divMenu2, 'show', function(mN) { this.ieSelBoxFixShow(mN) }, 1);
addEvent(divMenu2, 'hide', function(mN) { this.ieSelBoxFixHide(mN) }, 1);


