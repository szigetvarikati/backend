// automatically generated by the FlatBuffers compiler, do not modify

package Contract;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.BooleanVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.DoubleVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.ShortVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Struct;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.UnionVector;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class SelectAllSound extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_23_5_26(); }
  public static SelectAllSound getRootAsSelectAllSound(ByteBuffer _bb) { return getRootAsSelectAllSound(_bb, new SelectAllSound()); }
  public static SelectAllSound getRootAsSelectAllSound(ByteBuffer _bb, SelectAllSound obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public SelectAllSound __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public boolean withBinaryData() { int o = __offset(4); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createSelectAllSound(FlatBufferBuilder builder,
      boolean withBinaryData) {
    builder.startTable(1);
    SelectAllSound.addWithBinaryData(builder, withBinaryData);
    return SelectAllSound.endSelectAllSound(builder);
  }

  public static void startSelectAllSound(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addWithBinaryData(FlatBufferBuilder builder, boolean withBinaryData) { builder.addBoolean(0, withBinaryData, false); }
  public static int endSelectAllSound(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public SelectAllSound get(int j) { return get(new SelectAllSound(), j); }
    public SelectAllSound get(SelectAllSound obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

